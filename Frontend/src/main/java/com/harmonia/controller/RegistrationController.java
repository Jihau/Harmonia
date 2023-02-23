package com.harmonia.controller;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.UserClient;
import com.harmonia.po.UserPO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

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
        
        UserClient userclient = UserClient.getInstance();

        String email = emailField.getText();
        String username = emailField.getText();

        String pword = passwordField.getText();
        String RepeatPword = repeatPasswordField.getText();

        UserPO addMe = new UserPO();
        
        addMe.setEmail(email);
        addMe.setPassword(pword);
        addMe.setUsername(username);
        addMe.setProfileIcon("https://www.eurokolikot.com/image/cache/data/10800/suomi-2022-2-euro-kansallisbaletti-unc-1-200x200.jpg");

        if (pword.equals(RepeatPword)) {
            System.out.println("Passwords match");
            try {
                System.out.println("Attempting HTTP");
                userclient.addUser(addMe);
                System.out.println("HTTP done");

                Alert createdAlert = new Alert(AlertType.INFORMATION);
                createdAlert.setTitle("Account created");
                createdAlert.setContentText("click to go to login");
                createdAlert.showAndWait();

                FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("intro-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                Stage stage = (Stage) RegisterButton.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Harmonia");
                stage.show();

            } catch (Exception e) {
                Alert ServerAlert = new Alert(AlertType.ERROR);
                ServerAlert.setTitle("Server error");
                ServerAlert.setContentText("Please read stack trace to debug");
                ServerAlert.showAndWait();
            }
        } else {
            Alert passwordAlert = new Alert(AlertType.WARNING);
                passwordAlert.setHeaderText("Passwords do not match");
                passwordAlert.setContentText("The passwords you entered do not match. Please re-enter your passwords.");
                passwordAlert.show();
        }
    }
    

}
