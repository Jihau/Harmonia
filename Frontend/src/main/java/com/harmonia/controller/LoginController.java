package com.harmonia.controller;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.UserClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.po.UserPO;
import com.harmonia.utils.HarmoniaUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Locale;
import java.util.Objects;

/**
 * Controller class for the login view. Handles user authentication and
 * navigation to other views.
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

    @FXML
    Label usernameLabel;

    @FXML
    Label passwordLabel;

    @FXML
    Label appTitle;

    /**
     * Handles the event when the login button is clicked. Checks that both fields
     * are filled and the information matches.
     * If successful, redirects the user to the Harmonia view.
     */

    public void initialize() {
        if (HarmoniaConstants.selectedLocale == null) {
            HarmoniaConstants.setLanguage(Locale.getDefault());
        }
        usernameLabel.setText(HarmoniaConstants.textconstants.usernameLabelText);
        passwordLabel.setText(HarmoniaConstants.textconstants.passwordLabelText);
        registerLink.setText(HarmoniaConstants.textconstants.registerLinkText);
        loginButton.setText(HarmoniaConstants.textconstants.loginText);
        // Setting Contenful Labels
        usernameLabel.setText(HarmoniaUtils.getLabelForIdentifier("username", usernameLabel.getText()));
        passwordLabel.setText(HarmoniaUtils.getLabelForIdentifier("password", passwordLabel.getText()));
        appTitle.setText(HarmoniaUtils.getLabelForIdentifier("harmonia", appTitle.getText()));
    }

    @FXML
    public void onLoginButtonClick() {

        if (Objects.equals(usernameField.getText(), "") || Objects.equals(passwordField.getText(), "")) {
            errorLabel.setText(HarmoniaConstants.textconstants.loginErrorLabelText);
            errorLabel.setVisible(true);
            return;
        }
        errorLabel.setText(HarmoniaConstants.textconstants.loginErrorLabelText);
        errorLabel.setVisible(true);
        if (login(usernameField.getText(), passwordField.getText())) {
            logInRedirect();
        }
    }

    /**
     * Handles the event when the register link is clicked. Redirects the user to
     * the registration view.
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
            HarmoniaUtils.loadJavaFxView("Harmonia", "harmonia-view.fxml", (Stage) loginButton.getScene().getWindow());
        } catch (Exception e) {
            System.out.println("Redirect failed");
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