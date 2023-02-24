package com.harmonia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class representing the running class of the Harmonia application.
 *
 * @author OTP1 Group 3: Johanna Toivanen, Mohammed Al-Jewari, Sampo Savolainen, Jesper Ojala
 * @version 1.0
 * @since 1.0
 */

public class HarmoniaApplication extends Application {
    /**
     * Runs the app *
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("usersettings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Harmonia");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}