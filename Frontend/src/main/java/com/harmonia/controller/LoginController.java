package com.harmonia.controller;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.UserClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.po.UserPO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

/**
 * Controller class for the login view. Handles user authentication and navigation to other views.
 *
 * @author Harmonia team
 * @version 1.0
 */
public class LoginController {

    /**
     * Represents a client for interacting with the user service.
     */
    UserClient userClient = new UserClient();

    /**
     * Represents a text field for entering a username.
     */
    @FXML
    TextField usernameField;
    /**
     * Represents a password field for entering a password.
     */
    @FXML
    PasswordField passwordField;

    /**
     * Represents a hyperlink for registering a new account.
     */
    @FXML
    Hyperlink registerLink;

    /**
     * Represents a button for submitting a login request.
     */
    @FXML
    Button loginButton;

    /**
     * Represents a label for displaying error messages.
     */
    @FXML
    Label errorLabel;


    /**
     * Handles the event when the login button is clicked. Checks that both fields are filled and the information matches.
     * If successful, redirects the user to the Harmonia view.
     */
    @FXML
    public void onLoginButtonClick() {

        if (Objects.equals(usernameField.getText(), "") || Objects.equals(passwordField.getText(), "")) {
            errorLabel.setText("Please fill both fields!");
            errorLabel.setVisible(true);
            return;
        }
        errorLabel.setText("Login failed");
        errorLabel.setVisible(true);
        if (login(usernameField.getText(), passwordField.getText())) {
            logInRedirect();
        }
    }

    /**
     * Handles the event when the register link is clicked. Redirects the user to the registration view.
     */
    @FXML
    protected void onRegisterLinkClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("registration-view.fxml"));
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
     * Redirects the user to the Harmonia view after a successful login.
     */
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

    /**
     * Authenticates the user with the provided username and password.
     * If successful, sets the logged in user to the user retrieved from the server.
     * Returns true if the authentication was successful, false otherwise.
     */
    public boolean login(String username, String password) {
        ResponseEntity<UserPO> user = userClient.validate(username, password);
        if (user.getStatusCode().equals(HttpStatus.OK)) {
            HarmoniaConstants.LOGGED_USERS = user.getBody();
            return true;
        }
        HarmoniaConstants.LOGGED_USERS = null;
        return false;
    }
}