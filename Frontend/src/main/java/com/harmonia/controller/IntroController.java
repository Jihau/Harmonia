package com.harmonia.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class IntroController {
    @FXML
    Button Loginbutton;

    @FXML 
    Button RegisterButton;

    @FXML
    protected void LoginButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Stage stage = (Stage) Loginbutton.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1020, 740);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrationView.fxml"));
            Stage stage = (Stage) RegisterButton.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1020, 740);
            stage.setScene(scene);
            stage.setTitle("Register to Harmonia");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
