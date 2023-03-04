package com.harmonia.controller;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.ServerMemberClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.po.ServerMemberPO;
import com.harmonia.po.ServerPO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class CommunitiesController {

    private ServerMemberClient memberClient;

    private ObservableList<ServerPO> myServers;

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

    /*
    @FXML
    protected void onmcHomePageBtnClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("usersettings-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) mcHomePageBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign in to Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    public void initialize() {
        this.memberClient = new ServerMemberClient();
        this.myServers = FXCollections.observableArrayList();

        myServers.setAll(memberClient.listServersByMemberId(HarmoniaConstants.LOGGED_USERS.getUserId()));

        populateServerListView();
    }
    private void populateServerListView() {

        ServerMemberPO[] servers = memberClient.listServersByMemberId(HarmoniaConstants.LOGGED_USERS.getUserId());
        for (ServerMemberPO server : servers) {
            serverList.getItems().add(server.getServerName());
        }
        
    }
}
