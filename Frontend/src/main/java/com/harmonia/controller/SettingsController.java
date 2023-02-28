package com.harmonia.controller;

import java.io.IOException;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.UserClient;
import com.harmonia.po.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingsController {
    private UserPO user;

    UserClient userclient;
    @FXML
    AnchorPane root;

    @FXML
    TextField UsernameField;

    @FXML
    TextField profImgField;

    @FXML
    PasswordField passwordField;

    @FXML
    PasswordField oldPasswordField;

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

    public void initialize() {

        /* placeholder, get user from session */

        System.out.println("initializing");

        this.user = new UserPO();

        this.user.setUserId(18);
        this.user.setUsername("jokke");
        this.user.setEmail("jokke@user.com");
        this.user.setProfileIcon("https://vignette.wikia.nocookie.net/awesomeanimeandmanga/images/3/34/K-on%21-avatar-200x200.jpg/revision/latest?cb=20110517050049");
        
        /* placeholder, get user from session */

        UsernameField.setText(this.user.getUsername());
        profImgField.setText(this.user.getProfileIcon());

    }

    public void onSaveButtonClick() {

        userclient = new UserClient();

        UserPO user = this.getUser();

        System.out.println("Updating user:");

        System.out.println("validating");

        if (!passwordField.getText().equals("") && UserClient.validate(user.getUsername(), oldPasswordField.getText()).value()==200) {

            System.out.println("new password detected and old password checked");

            try {
                System.out.println("Trying to put with new password");

                userclient = new UserClient();

                System.out.println("Client opened");

                user.setProfileIcon(profImgField.getText());
                user.setPassword(passwordField.getText());

                userclient.editPassword(user);
                userclient.editIcon(user);

                successAlert();
                returnToMain();
                
            } catch (Exception e) {
                System.out.println("Failed to update user");
                e.printStackTrace();
            }
        } else if (passwordField.getText()=="") {

            System.out.println("Trying to put without new password");

            try {
                
                if (UserClient.validate(user.getUsername(), oldPasswordField.getText()).value()==200) {
                    user.setProfileIcon(profImgField.getText());
                    userclient.editIcon(user);
                }

                successAlert();
                
                returnToMain();

            } catch (Exception e) {
                Alert failedalert = new Alert(AlertType.ERROR);
                failedalert.setContentText("Failed to update user");
                failedalert.show();
                System.out.println("Failed to update user");
            }
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
}
