package com.harmonia.controller;

import com.harmonia.HarmoniaApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class CommunitiesController {

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=settings
     */
    @FXML
    private Button mcFriendsBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=settings
     */
    @FXML
    private Button mcSettingsBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=settings
     */
    @FXML
    private Button mcProfileBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=settings
     */
    @FXML
    private Button mcHomePageBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=settings
     */
    @FXML
    private Button mcCommunityBtn;

    /*
    @FXML
    protected void onmcHomePageBtnClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("usersettings-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) mcHomePageBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign in to Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}
