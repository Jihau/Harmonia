package com.harmonia.controller;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.ServerClient;
import com.harmonia.client.ServerMemberClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.po.ServerMemberPO;
import com.harmonia.po.ServerPO;

import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CommunitiesController {

    private ServerMemberClient memberClient;
    private ArrayList<ServerPO> myServers;

    @FXML
    private ListView<String> serverList;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=settings
     */
    @FXML
    private Button mcFriendsBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=settings
     */
    @FXML
    private Button mcSettingsBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=settings
     */
    @FXML
    private Button mcProfileBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=settings
     */
    @FXML
    private Button mcHomePageBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=settings
     */
    @FXML
    private Button mcCommunityBtn;

    
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
    

    public void initialize() {
        this.memberClient = new ServerMemberClient();
        this.myServers = new ArrayList<>();

        populateServerListView();

        serverList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getTarget()==null) {
                    System.out.println("Null found");
                } else {
                    ServerPO selected = myServers.get(serverList.getSelectionModel().getSelectedIndex());

                    sendToServer(event, selected);
                }
            }
        });
    }
    private void populateServerListView() {

        ServerMemberPO[] servers = memberClient.listServersByMemberId(HarmoniaConstants.LOGGED_USERS.getUserId());
        for (ServerMemberPO server : servers) {
            serverList.getItems().add(server.getServerName());
        }
        
    }

    private void sendToServer(MouseEvent event ,ServerPO sendHere) {
        ServerPO server = sendHere;
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(HarmoniaApplication.class.getResource("server-view.fxml"));
            stage.setUserData(server);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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


}
