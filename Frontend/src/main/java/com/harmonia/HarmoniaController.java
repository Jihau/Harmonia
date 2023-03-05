package com.harmonia;

import com.harmonia.client.ServerClient;
import com.harmonia.client.ServerMemberClient;
import com.harmonia.constants.HarmoniaConstants;
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

public class HarmoniaController {


    @FXML
    public Button logoutButton;

    @FXML
    private Button hHomePageBtn;

    @FXML
    private Button hFriendsBtn;

    @FXML
    private Button hProfileBtn;

    @FXML
    private Button hSettingsBtn;

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

            // Create a new ServerMemberPO object with the current member's ID and the selected server's ID
            ServerMemberPO serverMemberPO = new ServerMemberPO();
            serverMemberPO.setMemberId(HarmoniaConstants.LOGGED_USERS.getUserId());
            serverMemberPO.setServerId(selectedServer.getServerId());

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
        }
    }

    /**
     * This method is called when the home page button is clicked. It loads the harmonia-view.fxml file and sets it as the new scene for the window.
     */
    @FXML
    protected void onhHomePageBtnClick() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("harmonia-view.fxml"));
            Stage stage = (Stage) hHomePageBtn.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Explore");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the Direct Message button is clicked. It loads the messages-view.fxml file and sets it as the new scene for the window.
     */
    @FXML
    protected void onhFriendsBtnClick() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("messages-view.fxml"));
            Stage stage = (Stage) hFriendsBtn.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Friends & messages");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the profile button is clicked. It loads the profile-view.fxml file and sets it as the new scene for the window.
     */
    @FXML
    protected void onhProfileBtnClick() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("profile-view.fxml"));
            Stage stage = (Stage) hProfileBtn.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Profile");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the Settings button is clicked. It loads the settings-view.fxml file and sets it as the new scene for the window.
     */
    @FXML
    protected void onhSettingsBtnClick() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("usersettings-view.fxml"));
            Stage stage = (Stage) hSettingsBtn.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Profile");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Handles the action when the logout button is clicked. Loads the login
     * UI / login view again.
     */

    public void logoutOnButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("login-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign in to Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the Servers button is clicked. It loads the mycommunities-view.fxml file and sets it as the new scene for the window.
     */
    public void onServerButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("mycommunities-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Your communities");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}