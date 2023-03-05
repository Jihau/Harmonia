package com.harmonia.controller;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.UserClient;
import com.harmonia.po.UserPO;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * The RegistrationController class manages the user registration process
 * by handling user inputs, validating them, and sending them to the server
 * through the UserClient class.
 *
 * @author Harmonia team
 * @version 1.0
 */
public class RegistrationController {

    /**
     * The root pane of the registration scene.
     */
    @FXML
    AnchorPane root;

    /**
     * The text field for the user to enter their email during registration.
     */
    @FXML
    TextField emailField;

    /**
     * The text field for the user to enter their username during registration.
     */
    @FXML
    TextField usernameField;

    /**
     * The text field for the user to enter their password during registration.
     */
    @FXML
    PasswordField passwordField;

    /**
     * The text field for the user to repeat their password during registration.
     */
    @FXML
    PasswordField repeatPasswordField;

    @FXML
    Button RegisterButton;

    /**
     * The label to display any errors during registration.
     */
    @FXML
    Label errorLabel;

    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

    /**
     * Called when the RegisterButton is clicked. It retrieves user input values from the
     * registration form, validates them, sends them to the server for storage, and
     * redirects the user to the login page upon successful registration.
     */
    @FXML
    public synchronized void onRegisterButtonClick() {
        UserClient userclient = new UserClient();

        String email = emailField.getText();
        String username = usernameField.getText();

        String pword = passwordField.getText();
        String RepeatPword = repeatPasswordField.getText();

        UserPO addMe = new UserPO();

        addMe.setEmail(email);
        addMe.setPassword(pword);
        addMe.setUsername(username);
        addMe.setProfileIcon("https://www.eurokolikot.com/image/cache/data/10800/suomi-2022-2-euro-kansallisbaletti-unc-1-200x200.jpg");

        if (email != "" && username != "" && pword != "" && pword.equals(RepeatPword)) {
            System.out.println("Passwords match");
            try {
                System.out.println("Attempting HTTP");
                userclient.addUser(addMe);
                System.out.println("HTTP done");

                errorLabel.setVisible(true);
                errorLabel.setText("Registration succesful, wait...");
                System.out.println("waiting 3 and redirecting");
                redirectToLogin();

            } catch (Exception e) {
                Alert ServerAlert = new Alert(AlertType.ERROR);
                ServerAlert.setTitle("Server error");
                ServerAlert.setContentText("Please read stack trace to debug");
                ServerAlert.showAndWait();
            }
        } else if (emailField.getText() == "" || usernameField.getText() == "" || passwordField.getText() == "" || repeatPasswordField.getText() == "") {
            errorLabel.setVisible(true);
        }

        if (!passwordField.getText().equals(repeatPasswordField.getText())) {
            Alert passwordAlert = new Alert(AlertType.WARNING);
            passwordAlert.setHeaderText("Passwords do not match");
            passwordAlert.setContentText("The passwords you entered do not match. Please re-enter your passwords.");
            passwordAlert.show();
        }
    }

    /**
     * Redirects the user to the login page when the "return" link is clicked.
     * Loads the "login-view.fxml" file and sets it as the scene for the stage.
     * Sets the title of the stage to "Harmonia".
     *
     * @throws IOException if an input/output error occurs while loading the FXML file
     */
    public void returnLinkOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Harmonia");
        stage.show();
    }

    /**
     * Redirects the user to the login page.
     *
     * @throws InterruptedException if the thread is interrupted
     * @throws IOException          if there is an input/output error
     */
    private void redirectToLogin() throws InterruptedException, IOException {
        TimeUnit.SECONDS.sleep(3);

        FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Harmonia");
        stage.show();
    }

}
