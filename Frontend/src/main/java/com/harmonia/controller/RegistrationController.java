package com.harmonia.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.UserClient;
import com.harmonia.po.UserPO;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegistrationController {
    @FXML
    AnchorPane root;

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
    Label errorLabel;
    
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

        if (email!="" && username!="" && pword!="" && pword.equals(RepeatPword)) {
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
        } else if (emailField.getText()=="" || usernameField.getText()=="" || passwordField.getText()=="" || repeatPasswordField.getText()=="") {
            errorLabel.setVisible(true);
        }

        if (!passwordField.getText().equals(repeatPasswordField.getText())) {
                Alert passwordAlert = new Alert(AlertType.WARNING);
                passwordAlert.setHeaderText("Passwords do not match");
                passwordAlert.setContentText("The passwords you entered do not match. Please re-enter your passwords.");
                passwordAlert.show();
        }
    }

    public void returnLinkOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Harmonia");
        stage.show();
    }

    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

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
