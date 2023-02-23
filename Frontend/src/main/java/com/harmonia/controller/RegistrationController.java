package com.harmonia.controller;

import com.dlsc.formsfx.model.structure.PasswordField;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistrationController {
    
    @FXML
    TextField emailField;

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    @FXML
    PasswordField repeatPasswordField;

    @FXML
    Button RegisterButton;
    
    @FXML
    public void onRegisterButtonClick() {
        String email = emailField.getText();
        String username = emailField.getText();

        String pword = passwordField.getValue();
        String RepeatPword = repeatPasswordField.getValue();

        
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
