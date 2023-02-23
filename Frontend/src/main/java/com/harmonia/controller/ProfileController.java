package com.harmonia.controller;

import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

import com.harmonia.HarmoniaApplication;
import com.harmonia.po.UserPO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProfileController {
    private UserPO friend = new UserPO();
    /* placeholder, get from DB on load later */

    @FXML
    AnchorPane root;

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

    public void initialize() {
        friend.setEmail("example@example.com");
        friend.setUsername("marko");
        friend.setUserId(123);
        friend.setProfileIcon("https://vignette.wikia.nocookie.net/awesomeanimeandmanga/images/3/34/K-on%21-avatar-200x200.jpg/revision/latest?cb=20110517050049");

        try {
            Image profImage = new Image(friend.getProfileIcon());    
            profileImage.setImage(profImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
                

        UsernameText.setText(friend.getUsername());
        UserIdText.setText("#" + String.valueOf(friend.getUserId()));
    }

    @FXML
    public void onFListButtonClick(ActionEvent click) {
        System.out.println("Changing to friendslist-view");
        changeScene("messages-view.fxml");
    }

    @FXML public void onProfileToChannelsButtonClick(ActionEvent click) {
        System.out.println("changing to intro-view");
        changeScene("intro-view.fxml");
    }

    @FXML
    public void onProfileToSettingsButtonClick(ActionEvent click) {
        System.out.println("changing to settings-view");
        changeScene("settings-view.fxml");
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

}
