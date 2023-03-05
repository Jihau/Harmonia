package com.harmonia.controller;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.UserClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.po.UserPO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ProfileController {
    @FXML
    public Label BioText;

    @FXML
    public Label BioLabel;

    @FXML
    public Button editBioBtn;

    @FXML
    public TextField bioTextField;

    UserClient userclient = new UserClient();
    @FXML
    AnchorPane root;
    /* placeholder, get from DB on load later */
    @FXML
    Button ProfileToFriendButton;
    @FXML
    Button profileToChannelsButton;
    @FXML
    Button profiletohomeButton;
    @FXML
    ImageView profileImage;
    @FXML
    Label UserIdText;
    @FXML
    Label UsernameText;
    private UserPO user = new UserPO();
    /**
     * navigation button for nav menu
     * letter combination before name indicates in what view the button is from
     * h=harmonia-view
     * fm=messages-view
     * p=profile-view
     * s=settings
     */
    @FXML
    private Button pFriendsBtn;

    /**
     * navigation button for nav menu
     * letter combination before name indicates in what view the button is from
     * h=harmonia-view
     * fm=messages-view
     * p=profile-view
     * s=settings
     */
    @FXML
    private Button pSettingsBtn;

    /**
     * navigation button for nav menu
     * letter combination before name indicates in what view the button is from
     * h=harmonia-view
     * fm=messages-view
     * p=profile-view
     * s=settings
     */
    @FXML
    private Button pProfileBtn;

    /**
     * navigation button for nav menu
     * letter combination before name indicates in what view the button is from
     * h=harmonia-view
     * fm=messages-view
     * p=profile-view
     * s=settings
     */
    @FXML
    private Button pHomePageBtn;

    /**
     * navigation button for nav menu
     * letter combination before name indicates in what view the button is from
     * h=harmonia-view
     * fm=messages-view
     * p=profile-view
     * s=settings
     */
    @FXML
    private Button pCommunityBtn;

    /**
     * Handles the action when the login button is clicked. Loads the main
     * UI / main app view.
     */
    @FXML
    private Button logoutButton;

    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }

    public void initialize() {
        user = HarmoniaConstants.LOGGED_USERS;

        try {
            Image profImage = new Image(user.getProfileIcon());
            profileImage.setImage(profImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UsernameText.setText(user.getUsername());
        UserIdText.setText("#" + user.getUserId());
        BioText.setText(HarmoniaConstants.LOGGED_USERS.getBio());
        System.out.println(BioText.getText());
    }

    @FXML
    public void onFListButtonClick(ActionEvent click) {
        System.out.println("Changing to friendslist-view");
        changeScene("messages-view.fxml");
    }

    @FXML
    public void onProfileToChannelsButtonClick(ActionEvent click) {
        System.out.println("changing to intro-view");
        changeScene("intro-view.fxml");
    }

    @FXML
    public void onProfileToSettingsButtonClick(ActionEvent click) {
        System.out.println("changing to settings-view");
        changeScene("usersettings-view.fxml");
    }

    @FXML
    private void changeScene(String fxmlFile) {
        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("registration-view.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource(fxmlFile));
            System.out.println(getClass().getResource(fxmlFile));
            Stage stage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1020, 740);
            stage.setScene(scene);
            stage.setTitle("Register to Harmonia");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onpHomePageBtnClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("harmonia-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) pHomePageBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign in to Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onpFriendsBtnClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("messages-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) pFriendsBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign in to Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onpSettingsBtnClick() {
        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("usersettings-view.fxml"));
            Stage stage = (Stage) pSettingsBtn.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setScene(scene);
            stage.setTitle("Profile");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onpServerButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("mycommunities-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Your communities");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logoutOnButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("login-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign in to Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editBioTextField(ActionEvent event) {

    }

    public void editBioText() {

        try {
            UserPO userPO = new UserPO();
            userPO.setUserId(HarmoniaConstants.LOGGED_USERS.getUserId());
            userPO.setBio(bioTextField.getText());
            System.out.println(userPO.getUserId());
            HarmoniaConstants.LOGGED_USERS = userclient.editBio(userPO);
        } catch (Exception e) {
            e.printStackTrace();
            Alert failedalert = new Alert(Alert.AlertType.ERROR);
            failedalert.setContentText("Failed to update user");
            failedalert.show();
            System.out.println("Failed to update user");
        }
    }

}
