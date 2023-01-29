package com.harmonia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class HarmoniaController {
    public TextField emailField;
    public PasswordField repeatPasswordField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label welcomeText;

    @FXML
    private Hyperlink registerLink;

    @FXML
    private Button closeButton;

    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        welcomeText.setText("Welcome to Harmonia!");
    }

    @FXML
    protected void onRegisterLinkClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrationView.fxml"));
            Stage stage = (Stage) registerLink.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Register to Harmonia");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onCloseButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1020, 740);
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}