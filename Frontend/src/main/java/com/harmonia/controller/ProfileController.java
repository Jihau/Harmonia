package com.harmonia.controller;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.UserClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.po.UserPO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

/**
 * This class extends MainViewController to control the user profile page.
 *
 * @author Harmonia team
 * @version 1.0
 */

public class ProfileController extends MainViewController {

    /**
     * The label to display the user's bio text.
     */
    @FXML
    public Label BioText;

    /**
     * The label to display the "Bio" text.
     */
    @FXML
    public Label BioLabel;

    /**
     * The button to edit the user's bio.
     */
    @FXML
    public Button editBioBtn;

    /**
     * The text field to input the user's bio.
     */
    @FXML
    public TextField bioTextField;

    /**
     * The user client used to edit the user's bio.
     */
    UserClient userclient = new UserClient();

    /**
     * The root anchor pane of the profile page.
     */
    @FXML
    AnchorPane root;

    /**
     * The image view to display the user's profile image.
     */
    @FXML
    ImageView profileImage;

    /**
     * The label to display the user's user ID.
     */
    @FXML
    Label UserIdText;

    /**
     * The label to display the user's username.
     */
    @FXML
    Label UsernameText;


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

    /**
     * Initializes the profile page with the user's information.
     */
    public void initialize() {
        UserPO user = HarmoniaConstants.LOGGED_USERS;

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

    public void editBioTextField(ActionEvent event) {

    }

    /**
     * Edits the user's bio text.
     *
     * If successful, updates the user's bio text in HarmoniaConstants.LOGGED_USERS.
     *
     * If unsuccessful, displays an error alert.
     */
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
