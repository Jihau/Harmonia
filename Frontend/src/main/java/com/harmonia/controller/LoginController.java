package com.harmonia.controller;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.UserClient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    TextField usernameField;
    
    @FXML
    PasswordField passwordField;

    @FXML
    Hyperlink toregisterLink;

    @FXML
    Button loginButton;

    @FXML
    Label errorLabel;

    public boolean login(String username, String password) {

        return UserClient.validate(username, password).value()==200;     
    }

    @FXML
    public void logInRedirect() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("harmonia-view.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Harmonia");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    @FXML
    public void onLoginButtonClick() {
    
    if (usernameField.getText()=="" || passwordField.getText()=="") { errorLabel.setVisible(true); return;}
        if (login(usernameField.getText(), passwordField.getText())) {
            logInRedirect();
        }
    }

    @FXML
    protected void onRegisterLinkClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("registration-view.fxml"));
            Stage stage = (Stage) toregisterLink.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Register to Harmonia");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

}