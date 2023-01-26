package com.harmoniaApplication.harmonia;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HarmoniaController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onLoginButtonClick() {
        welcomeText.setText("Welcome to Harmonia!");
    }
}