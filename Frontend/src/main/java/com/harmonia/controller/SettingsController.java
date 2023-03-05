package com.harmonia.controller;

import com.harmonia.client.UserClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.po.UserPO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


/**
 * Controller class for the Settings view. Allows users to edit their profile image.
 * Extends MainViewController to enable return to home page functionality.
 *
 * @author Harmonia team
 * @version 1.0
 */
public class SettingsController extends MainViewController {

    /**
     * The client used to interact with the User API.
     */
    UserClient userclient = new UserClient();

    /**
     * The root node of the view.
     */
    @FXML
    AnchorPane root;

    /**
     * The text field for the user to input their profile picture URL.
     */
    @FXML
    TextField profImgField;

    /**
     * The button to submit the new profile picture URL.
     */
    @FXML
    Button submitButton;

    /**
     * The user object representing the currently logged-in user.
     */
    @FXML
    private UserPO user;

    /**
     * Initializes the view by setting the profile image text field to the user's current profile image.
     */
    public void initialize() {

        /* placeholder, get user from session */
        System.out.println("initializing");
        this.user = HarmoniaConstants.LOGGED_USERS;

        /* placeholder, get user from session */
        profImgField.setText(this.user.getProfileIcon());
    }

    /**
     * Handles the event when the user clicks the "Save" button. Updates the user's profile image in the database
     * and updates the displayed profile image if the update was successful.
     */
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
}
