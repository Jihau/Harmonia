package com.harmonia.controller;

import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.constants.HarmoniaMessagesConstants;
import com.harmonia.constants.HarmoniaViewsConstants;
import com.harmonia.utils.HarmoniaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainViewController {
    @FXML
    private Button logoutButton;
    @FXML
    private Button SettingsBtn;
    @FXML
    private Button ProfileBtn;
    @FXML
    private Button HomePageBtn;
    @FXML
    private Button CommunityBtn;
    @FXML
    private Button FriendsBtn;
    @FXML
    public Label HomeLabel;
    @FXML
    public Label ServersLabel;
    @FXML
    public Label DmLabel;
    @FXML
    public Label ProfileLabel;
    @FXML
    public Label SettingsLabel;
    @FXML
    public javafx.scene.shape.Rectangle IndicatorShape;

    /**
     * Handles the event when the Home Page button is clicked, which loads the Harmonia home page.
     *
     * @param event the event that triggered this method
     */
    @FXML
    protected void onfmHomePageBtnClick(ActionEvent event) {
        HarmoniaUtils.loadJavaFxView(HarmoniaConstants.messages.WINDOW_TITLE_HOME_MESSAGE, HarmoniaViewsConstants.HOME_VIEW, (Stage) HomePageBtn.getScene().getWindow());
    }

    /**
     * Handles the event when the Profile button is clicked, which loads the user's profile page.
     *
     * @param event the event that triggered this method
     */
    @FXML
    protected void onfmProfileBtnClick(ActionEvent event) {
        HarmoniaUtils.loadJavaFxView(HarmoniaConstants.messages.WINDOW_TITLE_MY_PROFILE_MESSAGE, HarmoniaViewsConstants.PROFILE_VIEW, (Stage) ProfileBtn.getScene().getWindow());
    }

    /**
     * Handles the event when the Settings button is clicked, which loads the user's settings page.
     *
     * @param event the event that triggered this method
     */
    @FXML
    protected void onfmSettingsBtnClick(ActionEvent event) {
        HarmoniaUtils.loadJavaFxView(HarmoniaConstants.messages.WINDOW_TITLE_MY_SETTINGS_MESSAGE, HarmoniaViewsConstants.USER_SETTINGS_VIEW, (Stage) SettingsBtn.getScene().getWindow());
        HomeLabel.setStyle("-fx-text-fill: #9f9f9f");
        IndicatorShape.setY(224);
        IndicatorShape.setX(195);
        SettingsLabel.setStyle("-fx-text-fill: #FFFFFF");
        System.out.println("MainViewController " + HomeLabel + " " + IndicatorShape + " " + SettingsLabel);
    }

    /**
     * Handles the event when the Servers button is clicked, which loads the user's settings page.
     *
     * @param event the event that triggered this method
     */
    @FXML
    public void onServerButtonClick(ActionEvent event) {
        HarmoniaUtils.loadJavaFxView(HarmoniaConstants.messages.WINDOW_TITLE_MY_SERVERS_MESSAGE, HarmoniaViewsConstants.USER_SERVERS_VIEW, (Stage) CommunityBtn.getScene().getWindow());
    }

    /**
     * Handles the event when the Logout button is clicked, which loads the user's settings page.
     *
     * @param event the event that triggered this method
     */
    @FXML
    public void logoutOnButtonClick(ActionEvent event) {
        HarmoniaUtils.loadJavaFxView(HarmoniaConstants.messages.WINDOW_TITLE_SIGN_IN_MESSAGE, HarmoniaViewsConstants.LOGIN_VIEW, (Stage) logoutButton.getScene().getWindow());
    }

    /**
     * Handles the event when the Messages button is clicked, which loads the user's settings page.
     *
     * @param event the event that triggered this method
     */
    @FXML
    protected void onsFriendsBtnClick(ActionEvent event) {
        HarmoniaUtils.loadJavaFxView(HarmoniaConstants.messages.WINDOW_TITLE_DIRECT_MESSAGES_MESSAGE, HarmoniaViewsConstants.DIRECT_MESSAGES_VIEW, (Stage) FriendsBtn.getScene().getWindow());
    }
}
