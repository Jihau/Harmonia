package com.harmonia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Class representing the main controller of the Harmonia application.
 *
 * @author OTP1 Group 3: Johanna Toivanen, Mohammed Al-Jewari, Sampo Savolainen, Jesper Ojala
 * @version 1.0
 * @since 1.0
 */

public class HarmoniaController {
    /**
     * The email field for the user in registration view.
     */
    @FXML
    public TextField emailField;

    /**
     * The repeat password field for the user in registration view.
     */
    @FXML
    public PasswordField repeatPasswordField;

    /**
     * The logout button.
     */
    @FXML
    public Button logoutButton;

    @FXML
    public Button loginButton;

    /**
     * The username field for the user.
     */
    @FXML
    private TextField usernameField;

    /**
     * The password field for the user.
     */
    @FXML
    private PasswordField passwordField;

    /**
     * The welcome text label.
     */
    @FXML
    private Label welcomeText;

    /**
     * The register link in registration view.
     */
    @FXML
    private Hyperlink registerLink;

    /**
     * The close button.
     */
    @FXML
    private Button closeButton;

    /**
     * Handles the action when the login button is clicked. Loads the main
     * UI / main app view.
     */
    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("harmonia-view.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Harmonia");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Handles the action when the login button is clicked. Loads the registration
     * UI / registration view.
     */
    @FXML
    protected void onRegisterLinkClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrationView.fxml"));
            Stage stage = (Stage) registerLink.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Register to Harmonia");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action when the login button is clicked. Loads the login
     * UI / login view.
     */
    @FXML
    protected void onCloseButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign in to Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Handles the action when the logout button is clicked. Loads the login
     * UI / login view again.
     */

    public void logoutOnButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign in to Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}