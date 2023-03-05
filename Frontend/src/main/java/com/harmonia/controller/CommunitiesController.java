package com.harmonia.controller;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.ServerClient;
import com.harmonia.client.ServerMemberClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.po.ServerMemberPO;
import com.harmonia.po.ServerPO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CommunitiesController {

    private ServerMemberClient memberClient;
    private ServerClient serverClient;
    private ArrayList<ServerPO> myServers;

    @FXML
    private ListView<String> serverList;
    @FXML
    private Button mcFriendsBtn;
    @FXML
    private Button mcSettingsBtn;
    @FXML
    private Button mcProfileBtn;
    @FXML
    private Button mcHomePageBtn;
    @FXML
    private Button mcCommunityBtn;

    /**
     * Handles the event when the home page button is clicked, which loads the Harmonia home page.
     *
     * @param event the event that triggered this method
     */
    @FXML
    protected void onmcHomePageBtnClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("harmonia-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) mcHomePageBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the CommunitiesController by setting up the necessary variables and loading any existing servers.
     */
    public void initialize() {
        this.serverClient = new ServerClient();
        this.memberClient = new ServerMemberClient();
        this.myServers = new ArrayList<>();

        populateServerListView();

        serverList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getTarget() == null) {
                    System.out.println("Null found");
                } else {
                    ServerPO selected = myServers.get(serverList.getSelectionModel().getSelectedIndex());

                    sendToServer(event, selected);
                }
            }
        });
    }

    /**
     * Lists the servers that user belongs to on the existing ListView called serverList
     */
    private void populateServerListView() {
        ServerMemberPO[] servers = memberClient.listServersByMemberId(HarmoniaConstants.LOGGED_USERS.getUserId());
        for (ServerMemberPO server : servers) {
            myServers.add(serverClient.getServerById(server.getServerId()));
            serverList.getItems().add(server.getServerName());
        }

    }

    /**
     * Handles the event when the servers button is clicked, which loads the view of the server that user selected.
     *
     * @param event    the event that triggered this method
     * @param sendHere sets user to the server
     */
    private void sendToServer(MouseEvent event, ServerPO sendHere) {

        ServerPO server = sendHere;
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        try {
            stage.setUserData(server);
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("server-view.fxml"));
            loader.setController(new ServerController(sendHere));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle(sendHere.getServerName());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Handles the event when the direct messages button is clicked, which loads the user's direct messages with friends/other users.
     */
    @FXML
    protected void onmcFriendsBtnClick() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("messages-view.fxml"));
            Stage stage = (Stage) mcFriendsBtn.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Friends & messages");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the event when the profile button is clicked, which loads the user's profile page.
     */
    @FXML
    protected void onmcProfileBtnClick() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("profile-view.fxml"));
            Stage stage = (Stage) mcProfileBtn.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Profile");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the event when the logout button is clicked, which logs out the user and returns them to login-view.
     */
    public void logoutOnButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("login-view.fxml"));
            Stage stage = (Stage) mcProfileBtn.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Login to Harmonia");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
