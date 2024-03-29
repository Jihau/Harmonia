package com.harmonia.controller;

import com.harmonia.client.UserClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.constants.HarmoniaViewsConstants;
import com.harmonia.po.UserPO;
import com.harmonia.utils.HarmoniaUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Locale;


/**
 * Controller class for the Settings view. Allows users to edit their profile image.
 * Extends MainViewController to enable return to home page functionality.
 *
 * @author Harmonia team
 * @version 1.0
 */
public class SettingsController extends MainViewController {

    /**
     * The client used to interact with the User API.
     */
    UserClient userclient = new UserClient();

    /**
     * The root node of the view.
     */
    @FXML
    AnchorPane root;

    @FXML
    Pane successLable;

    /**
     * Label shown when changing
     */
    @FXML
    ChoiceBox<String> LocaleDropdown;

    /**
     * The text field for the user to input their profile picture URL.
     */
    @FXML
    TextField profImgField;

    /**
     * The button to submit the new profile picture URL.
     */
    @FXML
    Button submitButton;

    @FXML
    Label changePfpText;

    @FXML
    Label settingsText;

    @FXML
    Label successLabelText;

    /**
     * The user object representing the currently logged-in user.
     */
    @FXML
    private UserPO user;

    /**
     * Initializes the view by setting the profile image text field to the user's current profile image
     * and populates the language selection dropdown.
     */
    public void initialize() {

        settingsText.setText(HarmoniaConstants.messages.WINDOW_TITLE_MY_SETTINGS_MESSAGE);
        changePfpText.setText(HarmoniaConstants.textconstants.changePfpText);
        submitButton.setText(HarmoniaConstants.textconstants.saveText);

        successLabelText.setText(HarmoniaConstants.messages.SETTINGS_EDIT_SUCCESS_LABEL_TEXT);

        ObservableList<Locale> languages = FXCollections.observableArrayList();
        languages.add(new Locale("English", "United Kingdom", "En"));
        languages.add(new Locale("Finnish", "Finland", "Fi"));
        languages.add(new Locale("Arabic", "Iraq", "Ar"));
        languages.add(new Locale("Chinese", "China", "Zh"));
        languages.add(new Locale("French", "France", "Fr"));
        languages.add(new Locale("German", "Germany", "De"));
        languages.add(new Locale("Hindi", "India", "Hi"));
        languages.add(new Locale("Italian", "Italy", "It"));
        languages.add(new Locale("Japanese", "Japan", "Ja"));
        languages.add(new Locale("Russian", "Russia", "Ru"));
        languages.add(new Locale("Spanish", "Spain", "Es"));
        languages.add(new Locale("Swedish", "Sweden", "Sv"));
        languages.add(new Locale("Persian", "Iran", "Fa"));

        ObservableList<String> languageTexts = FXCollections.observableArrayList();

        for (Locale locale : languages) {
            languageTexts.add(locale.getLanguage());
        }

        LocaleDropdown.setItems(languageTexts);

        if (HarmoniaConstants.selectedLocale != null) {
            int selectedIndex = languages.indexOf(HarmoniaConstants.selectedLocale);
            LocaleDropdown.getSelectionModel().select(selectedIndex);
        }

        LocaleDropdown.setOnAction(event -> {
            Locale selected = languages.get(LocaleDropdown.getSelectionModel().getSelectedIndex());
            HarmoniaConstants.setLanguage(selected);
            System.out.println(selected);
            //Refresh the page
            HarmoniaUtils.loadJavaFxView(HarmoniaConstants.messages.WINDOW_TITLE_MY_SETTINGS_MESSAGE, HarmoniaViewsConstants.USER_SETTINGS_VIEW, (Stage) LocaleDropdown.getScene().getWindow());
        });


        System.out.println("initializing");
        this.user = HarmoniaConstants.LOGGED_USERS;

        profImgField.setText(this.user.getProfileIcon());
    }

    /**
     * Handles the event when the user clicks the "Save" button. Updates the user's profile image in the database
     * and updates the displayed profile image if the update was successful.
     */
    public void onSaveButtonClick() {
        try {
            UserPO userPO = new UserPO();
            userPO.setUserId(HarmoniaConstants.LOGGED_USERS.getUserId());
            userPO.setProfileIcon(profImgField.getText());
            HarmoniaConstants.LOGGED_USERS = userclient.editIcon(userPO);
            profImgField.setText(HarmoniaConstants.LOGGED_USERS.getProfileIcon());
            successLable.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            Alert failedalert = new Alert(AlertType.ERROR);
            failedalert.setContentText(HarmoniaConstants.messages.PROFILE_EDIT_FAILED_MESSAGE_BODY);
            failedalert.show();
            System.out.println(HarmoniaConstants.messages.PROFILE_EDIT_FAILED_MESSAGE_BODY);
        }
    }

    /**
     * hides the success label.
     */
    public void onSuccessLabelClick() {
        successLable.setVisible(false);
    }
}
