package com.harmonia.controller;

import java.io.IOException;
import java.util.Objects;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.ServerClient;
import com.harmonia.client.ServerMemberClient;
import com.harmonia.client.UserClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.constants.HarmoniaConstants.*;
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

    @FXML
    private UserPO user;

    UserClient userclient = new UserClient();
    @FXML
    AnchorPane root;

    @FXML
    TextField profImgField;

    @FXML
    Button submitButton;

    Button sHomePageBtn;

    @FXML
    Button sCommunityBtn;

    @FXML
    Button sFriendsBtn;

    @FXML
    Button sProfileBtn;

    @FXML
    Button sSettingsBtn;

    @FXML
    Button logoutButton;

    public void initialize() {

        /* placeholder, get user from session */
        System.out.println("initializing");
        this.user = HarmoniaConstants.LOGGED_USERS;
        
        /* placeholder, get user from session */
        profImgField.setText(this.user.getProfileIcon());
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
