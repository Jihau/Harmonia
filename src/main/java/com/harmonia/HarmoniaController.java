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
    @FXML
    public TextField emailField;

    @FXML
    public PasswordField repeatPasswordField;

    @FXML
    public Button logoutButton;

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("harmonia-view.fxml"));
            Stage stage = (Stage) registerLink.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1020, 740);
            stage.setScene(scene);
            stage.setTitle("Harmonia");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void onRegisterLinkClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrationView.fxml"));
            Stage stage = (Stage) registerLink.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1020, 740);
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
            stage.setTitle("Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logoutOnButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1020, 740);
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign in to Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}