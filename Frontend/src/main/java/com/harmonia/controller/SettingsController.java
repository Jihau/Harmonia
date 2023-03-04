package com.harmonia.controller;

import java.io.IOException;
import java.util.Objects;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.ServerClient;
import com.harmonia.client.ServerMemberClient;
import com.harmonia.client.UserClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.po.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.http.HttpStatus;

public class SettingsController {

    UserClient userClient = new UserClient();

    @FXML
    private ListView<String> serverList;

    private ServerMemberClient serverMemberClient;

    private void populateServerListView() {
        ServerMemberPO[] servers = serverMemberClient.listServersByMemberId(HarmoniaConstants.LOGGED_USERS.getUserId());
        for (ServerMemberPO server : servers) {
            serverList.getItems().add(server.getServerName());
        }
    }

    private UserPO user;

    UserClient userclient = new UserClient();
    @FXML
    AnchorPane root;

    @FXML
    TextField UsernameField;

    @FXML
    TextField profImgField;

    @FXML
    Button submitButton;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=usersettings-view
     mc=mycommunities-view
     */
    @FXML
    Button sHomePageBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=usersettings-view
     mc=mycommunities-view
     */
    @FXML
    Button sCommunityBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=usersettings-view
     mc=mycommunities-view
     */
    @FXML
    Button sFriendsBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=usersettings-view
     mc=mycommunities-view
     */
    @FXML
    Button sProfileBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=usersettings-view
     mc=mycommunities-view
     */
    @FXML
    Button sSettingsBtn;

    @FXML
    Button logoutButton;

    public void initialize() {

        /* placeholder, get user from session */

        System.out.println("initializing");

        this.user = new UserPO();

        this.user.setUsername(HarmoniaConstants.LOGGED_USERS.getUsername());
        this.user.setEmail(HarmoniaConstants.LOGGED_USERS.getEmail());
        /* this.user.setProfileIcon("https://vignette.wikia.nocookie.net/awesomeanimeandmanga/images/3/34/K-on%21-avatar-200x200.jpg/revision/latest?cb=20110517050049"); */
        
        /* placeholder, get user from session */

        profImgField.setText(this.user.getProfileIcon());

        /*
        Listing servers
         */

        serverMemberClient = new ServerMemberClient();
        populateServerListView();
    }

    public void onSaveButtonClick() {
            try {
                UserPO userPO = new UserPO();
                userPO.setUserId(HarmoniaConstants.LOGGED_USERS.getUserId());
                userPO.setProfileIcon(profImgField.getText());
                HarmoniaConstants.LOGGED_USERS = userclient.editIcon(userPO);
                profImgField.setText(HarmoniaConstants.LOGGED_USERS.getProfileIcon());
            } catch (Exception e) {
                e.printStackTrace();
                Alert failedalert = new Alert(AlertType.ERROR);
                failedalert.setContentText("Failed to update user");
                failedalert.show();
                System.out.println("Failed to update user");
            }
        }


    private UserPO getUser() {
        return this.user;
    }

    @FXML
    public void onReturnButtonClick() throws IOException {
            returnToMain();
    }

    private void returnToMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("harmonia-view.fxml"));
            Stage stage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Harmonia");
            stage.show();
    }

    private void successAlert() {
        Alert UpdatedAlert = new Alert(AlertType.CONFIRMATION);
        UpdatedAlert.setHeaderText("User updated");
        UpdatedAlert.setContentText("Your user details were succesfully updated.");
        UpdatedAlert.setTitle("Success");
        UpdatedAlert.showAndWait();
    }

    @FXML
    protected void onsHomePageBtnClick() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("harmonia-view.fxml"));
            Stage stage = (Stage) sHomePageBtn.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Profile");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onsCommunityBtnClick() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("usersettings-view.fxml"));
            Stage stage = (Stage) sCommunityBtn.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Profile");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onsFriendsBtnClick() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("messages-view.fxml"));
            Stage stage = (Stage) sFriendsBtn.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Profile");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void onsProfileBtnClick() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("profile-view.fxml"));
            Stage stage = (Stage) sProfileBtn.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Profile");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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
}
