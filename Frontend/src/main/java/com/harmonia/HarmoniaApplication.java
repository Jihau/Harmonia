package com.harmonia;

import com.harmonia.constants.HarmoniaViewsConstants;
import com.harmonia.websockets.StompWebSocketClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Objects;

import static com.harmonia.utils.HarmoniaUtils.*;

/**
 * Class representing the running class of the Harmonia application.
 *
 * @author Harmonia Team
 * @version 2.0
 * @since 1.0
 */

public class HarmoniaApplication extends Application {
    public static void main(String[] args) {
        generateBackEndKey();
        loadContentfulLabels();
        new Thread(() -> StompWebSocketClient.startWebSocket()).start();
        launch();
    }

    /**
     * Runs the app.
     */
    @Override
    public void start(Stage stage) {
        try{
        FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource(HarmoniaViewsConstants.LOGIN_VIEW));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Harmonia");
        stage.getIcons().add(new Image(Objects.requireNonNull(HarmoniaApplication.class.getResourceAsStream("/com/harmonia/img/harmoniaIcon.png"))));
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        } catch (Throwable t){
            t.printStackTrace();
        }
    }

    /**
     * Exits the application.
     */
    @Override
    public void stop(){
        Runtime.getRuntime().exit(1);
    }
}
