package com.harmonia;

import com.harmonia.client.ChannelClient;
import com.harmonia.client.ServerClient;
import com.harmonia.client.ServerMemberClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.constants.HarmoniaData;
import com.harmonia.controller.MainViewController;
import com.harmonia.po.ChannelPO;
import com.harmonia.po.ServerMemberPO;
import com.harmonia.po.ServerPO;
import com.harmonia.utils.HarmoniaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * Class representing the main controller of the Harmonia's main view
 *
 * @author Harmonia Team
 * @version 2.0
 */

public class HarmoniaController extends MainViewController {

    @FXML
    private Pane serverJoinPopup;

    @FXML
    public Button logoutButton;

    @FXML
    private Button fmHomePageBtn;

    @FXML
    private Button sFriendsBtn;

    @FXML
    private Button fmProfileBtn;

    @FXML
    private Button fmSettingsBtn;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<Object> serverListView;

    @FXML
    private Label serverJoinPopupLabel;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private AnchorPane newServerDialogAnchor;

    @FXML
    private TextField serverNameField;

    @FXML
    private TextField serverCategoryField;

    @FXML
    private Label serverNameLabel;

    @FXML
    private Label serverCategoryLabel;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;


    private ServerClient serverClient = new ServerClient();
    private ServerMemberClient serverMemberClient = new ServerMemberClient();
    private ChannelClient channelClient = new ChannelClient();

    /**
     * sets labels for the main view
     */
    public void initialize() {
        searchBar.setPromptText(HarmoniaConstants.textconstants.searchCommunitiesHintText);
        serverJoinPopupLabel.setText(HarmoniaConstants.textconstants.serverJoinPopupText);
        cancelButton.setText(HarmoniaConstants.textconstants.cancelButtonLabel);
        serverNameLabel.setText(HarmoniaConstants.textconstants.serverNameText);
        serverCategoryLabel.setText(HarmoniaConstants.textconstants.serverCategoryLabelText);
    }

    /**
     * This method handles the user clicking the search button by retrieving the
     * search text from the searchBar
     * and calling the listServersByCategory() method from the ServerClient class
     * with the search text as the server
     * category. It then adds the server objects returned from the serverClient to
     * the serverListView.
     * 
     * @param event The event object that triggered this method.
     */
    @FXML
    private void handleSearchButtonAction(ActionEvent event) {
        // Get the text entered in the searchBar
        String searchText = searchBar.getText();

        // Call listServersByCategory() with the search text as the server category
        ServerPO[] servers = serverClient.listServersByCategory(searchText);

        // Clear the existing items in the serverListView
        serverListView.getItems().clear();

        // Add the server objects to the ListView
        for (ServerPO server : servers) {
            serverListView.getItems().add(server);
        }
    }

    /**
     * This method is called when a server is clicked in the server list view. It
     * adds the current user as a member of the selected server.
     *
     * @param event The MouseEvent object generated when a server is clicked in the
     *              server list view.
     */
    @FXML
    private void handleServerListClick(MouseEvent event) {
        System.out.println("handleServerListClick called");
        // Get the selected server from the serverListView
        Object selectedItem = serverListView.getSelectionModel().getSelectedItem();

        if (selectedItem instanceof ServerPO) {
            ServerPO selectedServer = (ServerPO) selectedItem;

            Boolean notJoined = true;

            for (ServerMemberPO member : serverMemberClient
                    .listMembersByServerId((long) selectedServer.getServerId())) {
                if (member.getMemberId() == HarmoniaConstants.LOGGED_USERS.getUserId()) {
                    notJoined = false;
                }
            }

            // Create a new ServerMemberPO object with the current member's ID and the
            // selected server's ID
            if (notJoined) {
                try {
                    joinServer(selectedServer.getServerId());
                } catch (Exception e) {
                    System.out.println("Failed to join server");
                    e.printStackTrace();
                }
            } else {
                System.out.println("You already are on this server!");
            }
        }
    }

    /**
     * This method makes the joined server label invisible.
     */
    public void onJoinLabelClick() {
        serverJoinPopup.setVisible(false);
    }

    /**
     * This method opens the new server dialog
     */
    public void onNewServerButtonClick() {
        System.out.println("Opening dialogue");
        newServerDialogAnchor.setVisible(true);
    }

    /**
     * This closes the new server dialogue
     */
    public void onCancelButtonClick() {
        System.out.println("Closing dialogue");
        newServerDialogAnchor.setVisible(false);
    }

    /**
     * Creates a new Server, adds an initial "General" text channel and joins the user to the newly created server.
     */
    public void onOkButtonClick() {
        String serverName = serverNameField.getText();
        String serverCat = serverCategoryField.getText();

        if (serverName.length() > 3 && serverCat.length() > 3) {
            
            ServerPO newServer = new ServerPO();
            try {
                newServer.setOwnerId(HarmoniaConstants.LOGGED_USERS.getUserId());
                newServer.setServerCategory(serverCat);
                newServer.setServerName(serverName);

                ServerPO created = serverClient.createServer(newServer);

                ChannelPO initialChannel = new ChannelPO();
                
                initialChannel.setChannelName("General");
                initialChannel.setChannelType("Text");
                initialChannel.setServerId(created.getServerId());

                channelClient.addChannel(initialChannel);

                joinServer(created.getServerId());

                newServerDialogAnchor.setVisible(false);

            } catch (Exception e) {
                System.out.println("Failed to create Server or join created server");
                e.printStackTrace();
            }
        } else {
            errorMessageLabel.setText(HarmoniaConstants.textconstants.nameTooShortErrorText);
        }

    }

    /**
     * Makes an attempt to join the user into the server matching the given server ID
     * @param serverId desired server ID
     */
    public void joinServer(int serverId) throws Exception {
        ServerMemberPO serverMemberPO = new ServerMemberPO();
        serverMemberPO.setMemberId(HarmoniaConstants.LOGGED_USERS.getUserId());
        serverMemberPO.setServerId(serverId);
        serverMemberPO.setNickName(HarmoniaConstants.LOGGED_USERS.getUsername());

        // Call addServerMember() with the new ServerMemberPO object
        ResponseEntity<ServerMemberPO> response = serverMemberClient.addServerMember(serverMemberPO);

        // Check the status code of the response
        if (response.getStatusCode() == HttpStatus.OK) {
            // Display a success message
            System.out.println("User added to server");
        } else {
            // Display an error message
            System.out.println("Failed to add user to server");
        }
        serverJoinPopup.setVisible(true);
    }

    /**
     * sets the error label to be an empty string.
     */
    public void clearErrorMessage() {
        errorMessageLabel.setText("");
    }
}