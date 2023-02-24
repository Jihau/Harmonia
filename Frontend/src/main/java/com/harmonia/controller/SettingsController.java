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

    public void initialize() {

        /* placeholder, get user from session */

        System.out.println("initializing");

        this.user = new UserPO();

        this.user.setUserId(14);
        this.user.setUsername("jokke");
        this.user.setEmail("jokke@gmail.com");
        this.user.setProfileIcon("https://vignette.wikia.nocookie.net/awesomeanimeandmanga/images/3/34/K-on%21-avatar-200x200.jpg/revision/latest?cb=20110517050049");
        this.user.setPassword("jokke123");

        
        /* placeholder, get user from session */

        UsernameField.setText(this.user.getUsername());
        profImgField.setText(this.user.getProfileIcon());

    }

    public void onSaveButtonClick() {

        /* I don't think this will work after getting the userdata from the database because of password hashing. We need to either hash the current password or validate this request with the backend */
        UserPO user = this.getUser();

        System.out.println("Updating user:");

        System.out.println(passwordField.getText());
        System.out.println(oldPasswordField.getText().equals(user.getPassword()));


        if (!passwordField.getText().equals("") && oldPasswordField.getText().equals(user.getPassword())) {

            System.out.println("new password detected and old password checked");

            try {
                System.out.println("Trying to put with new password");

                UserClient userclient = new UserClient();

                System.out.println("Client opened");

                System.out.println(UserClient.validate(user.getUsername(), user.getPassword()).value());

                if (UserClient.validate(user.getUsername(), user.getPassword()).value()==200);

                System.out.println("User validated");

                user.setUsername(UsernameField.getText());
                user.setProfileIcon(profImgField.getText());
                user.setPassword(passwordField.getText());

                userclient.editUser(user);
                
                Alert UpdatedAlert = new Alert(AlertType.CONFIRMATION);
                UpdatedAlert.setHeaderText("User updated");
                UpdatedAlert.setContentText("Your user details were succesfully updated.");
                UpdatedAlert.setTitle("Success");
                UpdatedAlert.showAndWait();

                
            } catch (Exception e) {
                System.out.println("Failed to update user");
                e.printStackTrace();
            }
        } else if (passwordField.getText()=="") {
            System.out.println("Trying to put without new password");
            try {
                UserClient userclient = new UserClient();
                
                user.setUsername(UsernameField.getText());
                user.setProfileIcon(profImgField.getText());

                Alert UpdatedAlert = new Alert(AlertType.CONFIRMATION);
                UpdatedAlert.setHeaderText("User updated");
                UpdatedAlert.setContentText("Your user details were succesfully updated.");
                UpdatedAlert.setTitle("Success");
                UpdatedAlert.showAndWait();
                
            } catch (Exception e) {
                System.out.println("Failed to update user");
            }
        }
    }

    private UserPO getUser() {
        return this.user;
    }

    @FXML
    public void onReturnButtonClick() throws IOException {
            
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("harmonia-view.fxml"));
            Stage stage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Harmonia");
            stage.show();
    }
}
