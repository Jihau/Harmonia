package com.harmonia;

import com.harmonia.client.ServerClient;
import com.harmonia.client.ServerMemberClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.controller.MainViewController;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * Class representing the main controller of the Harmonia's main view
 *
 * @author Harmonia Team
 * @version 1.0
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
    public TextField searchBar;
    @FXML
    public Button searchButton;
    @FXML
    public ListView<Object> serverListView;

    

    private ServerClient serverClient = new ServerClient();
    private ServerMemberClient serverMemberClient = new ServerMemberClient();

    /**
     * This method handles the user clicking the search button by retrieving the search text from the searchBar
      *and calling the listServersByCategory() method from the ServerClient class with the search text as the server
     * category. It then adds the server objects returned from the serverClient to the serverListView.
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
     * This method is called when a server is clicked in the server list view. It adds the current user as a member of the selected server.
     *
     * @param event The MouseEvent object generated when a server is clicked in the server list view.
     */
    @FXML
    private void handleServerListClick(MouseEvent event) {
        System.out.println("handleServerListClick called");
        // Get the selected server from the serverListView
        Object selectedItem = serverListView.getSelectionModel().getSelectedItem();



        if (selectedItem instanceof ServerPO) {
            ServerPO selectedServer = (ServerPO) selectedItem;

            Boolean notJoined = true;


            for (ServerMemberPO member : serverMemberClient.listMembersByServerId((long)selectedServer.getServerId())) {
                if (member.getMemberId() == HarmoniaConstants.LOGGED_USERS.getUserId()) { notJoined = false; }
            }

            // Create a new ServerMemberPO object with the current member's ID and the selected server's ID
            if (notJoined) {
                ServerMemberPO serverMemberPO = new ServerMemberPO();
                serverMemberPO.setMemberId(HarmoniaConstants.LOGGED_USERS.getUserId());
                serverMemberPO.setServerId(selectedServer.getServerId());
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
            } else {
                System.out.println("You already are on this server!");
            }
        }
    }
}