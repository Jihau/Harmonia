package com.harmonia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Class representing the running class of the Harmonia application.
 *
 * @author OTP1 Group 3: Johanna Toivanen, Mohammed Al-Jewari, Sampo Savolainen, Jesper Ojala
 * @version 1.0
 * @since 1.0
 */

public class HarmoniaApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    /**
     * Runs the app *
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Harmonia");
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
}
