package com.harmonia.controller;
import com.harmonia.*;

import com.harmonia.HarmoniaApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class IntroController {
    @FXML
    Button LoginButton;

    @FXML 
    Button RegisterButton;

    @FXML
    protected void LoginButtonClick() {
        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Stage stage = (Stage) LoginButton.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1020, 740);
            stage.setScene(scene);
            stage.setTitle("Harmonia");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void registerButtonClick() {
        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrationView.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistrationView.fxml"));
            Stage stage = (Stage) RegisterButton.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1020, 740);
            stage.setScene(scene);
            stage.setTitle("Register to Harmonia");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
