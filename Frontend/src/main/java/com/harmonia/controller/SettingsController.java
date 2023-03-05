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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.http.HttpStatus;

public class SettingsController extends MainViewController {

    @FXML
    private UserPO user;

    UserClient userclient = new UserClient();
    @FXML
    AnchorPane root;

    @FXML
    TextField profImgField;

    @FXML
    Button submitButton;

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
    public void onReturnButtonClick(ActionEvent event) throws IOException {
            super.onfmHomePageBtnClick(event);
    }

    private void successAlert() {
        Alert UpdatedAlert = new Alert(AlertType.CONFIRMATION);
        UpdatedAlert.setHeaderText("User updated");
        UpdatedAlert.setContentText("Your user details were succesfully updated.");
        UpdatedAlert.setTitle("Success");
        UpdatedAlert.showAndWait();
    }
}
