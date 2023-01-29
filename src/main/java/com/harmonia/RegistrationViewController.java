package com.harmonia;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class RegistrationViewController {
    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repeatPasswordField;

    @FXML
    private Label errorMessage;

    @FXML
    private Button closeButton;

    @FXML
    protected void onCloseButtonClick(ActionEvent event) {
        // logic to return to the login view
    }

    @FXML
    protected void onRegisterButtonClick() {
        // logic to check the input fields,
        // save the user data and return to the login view
        // or show error message
    }
}