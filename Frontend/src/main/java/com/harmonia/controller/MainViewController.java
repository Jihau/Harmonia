package com.harmonia.controller;

import com.harmonia.constants.HarmoniaMessagesConstants;
import com.harmonia.constants.HarmoniaViewsConstants;
import com.harmonia.utils.HarmoniaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainViewController {
    @FXML
    private Button logoutButton;
    @FXML
    private Button fmSettingsBtn;
    @FXML
    private Button fmProfileBtn;
    @FXML
    private Button fmHomePageBtn;
    @FXML
    private Button fmCommunityBtn;

    @FXML
    private Button sFriendsBtn;

    @FXML
    protected void onfmHomePageBtnClick(ActionEvent event) {
        HarmoniaUtils.loadJavaFxView(HarmoniaMessagesConstants.WINDOW_TITLE_HOME_MESSAGE, HarmoniaViewsConstants.HOME_VIEW, (Stage) fmHomePageBtn.getScene().getWindow());
    }

    @FXML
    protected void onfmProfileBtnClick(ActionEvent event) {
        HarmoniaUtils.loadJavaFxView(HarmoniaMessagesConstants.WINDOW_TITLE_MY_PROFILE_MESSAGE, HarmoniaViewsConstants.PROFILE_VIEW, (Stage) fmProfileBtn.getScene().getWindow());
    }

    @FXML
    protected void onfmSettingsBtnClick(ActionEvent event) {
        HarmoniaUtils.loadJavaFxView(HarmoniaMessagesConstants.WINDOW_TITLE_MY_SETTINGS_MESSAGE, HarmoniaViewsConstants.USER_SETTINGS_VIEW, (Stage) fmSettingsBtn.getScene().getWindow());
    }

    @FXML
    public void onServerButtonClick() {
        HarmoniaUtils.loadJavaFxView(HarmoniaMessagesConstants.WINDOW_TITLE_MY_SERVERS_MESSAGE, HarmoniaViewsConstants.USER_SERVERS_VIEW, (Stage) fmCommunityBtn.getScene().getWindow());
    }

    @FXML
    public void logoutOnButtonClick(ActionEvent event) {
        HarmoniaUtils.loadJavaFxView(HarmoniaMessagesConstants.WINDOW_TITLE_SIGN_IN_MESSAGE, HarmoniaViewsConstants.LOGIN_VIEW, (Stage) logoutButton.getScene().getWindow());
    }

    @FXML
    protected void onsFriendsBtnClick() {
        HarmoniaUtils.loadJavaFxView(HarmoniaMessagesConstants.WINDOW_TITLE_DIRECT_MESSAGES_MESSAGE, HarmoniaViewsConstants.DIRECT_MESSAGES_VIEW, (Stage) sFriendsBtn.getScene().getWindow());
    }
}
