package com.harmonia;

import com.harmonia.constants.HarmoniaViewsConstants;
import com.harmonia.utils.HarmoniaTaskRefresher;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

/**
 * Class representing the running class of the Harmonia application.
 *
 * @author Harmonia Team
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
        FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource(HarmoniaViewsConstants.LOGIN_VIEW));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Harmonia");
        stage.getIcons().add(new Image(Objects.requireNonNull(HarmoniaApplication.class.getResourceAsStream("/com/harmonia/img/harmoniaIcon.png"))));
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Platform.runLater(new HarmoniaTaskRefresher());
    }

    @Override
    public void stop(){
        Runtime.getRuntime().exit(1);
    }
}
