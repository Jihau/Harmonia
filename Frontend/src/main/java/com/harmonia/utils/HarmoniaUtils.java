package com.harmonia.utils;

import com.harmonia.HarmoniaApplication;
import com.harmonia.constants.HarmoniaMessagesConstants;
import com.harmonia.po.UserPO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Date;

/**
 * A utility class containing static methods that can be used across the application.
 *
 * @author Harmonia team
 * @version 1.0
 */
public class HarmoniaUtils {

    /**
     * Generates a HttpHeaders object with the content type set to JSON.
     *
     * @return HttpHeaders object with the content type set to JSON
     */
    public static HttpHeaders generateRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    /**
     * Simulates a user object for testing purposes.
     *
     * @return UserPO object representing a simulated user
     */
    public static UserPO simulateUser() {
        return new UserPO(4, "psoliz20320", "percysolizr@gmail.com", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQksVdR-HGuX9xo_u6KGBAwJPm4WN_1ivEMVkbEaD7vARYxV71r6T1nCPcxOLAjvMRXiZQ&usqp=CAU", "My bio", new Date());
    }

    /**
     * Loads a JavaFX view onto a given stage.
     *
     * @param title    the title of the view
     * @param viewName the name of the view file
     * @param stage    the stage onto which the view will be loaded
     */
    public static void loadJavaFxView(String title, String viewName, Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource(viewName));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays an error message dialog box.
     *
     * @param title       the title of the error message box
     * @param headerText  the header text of the error message box
     * @param contentText the content text of the error message box
     */
    public static void showErrorMessage(String title, String headerText, String contentText) {
        Alert notYourMessageAlert = new Alert(Alert.AlertType.ERROR);
        notYourMessageAlert.setTitle(title);
        notYourMessageAlert.setHeaderText(headerText);
        notYourMessageAlert.setContentText(contentText);
        notYourMessageAlert.showAndWait();
    }

    /**
     * Displays a confirmation message dialog box.
     *
     * @param confirmationTitle the title of the confirmation message box
     * @param title             the title of the message in the confirmation box
     * @param headerText        the header text of the confirmation message box
     * @param contentText       the content text of the confirmation message box
     * @return true if the user clicks "Yes" in the confirmation box, false otherwise
     */
    public static boolean showConfirmationMessage(String confirmationTitle, String title, String headerText, String contentText) {
        Alert confirmationMessage = new Alert(Alert.AlertType.CONFIRMATION, HarmoniaMessagesConstants.DIRECT_MESSAGES_DELETE_CONFIRMATION_MESSAGE, ButtonType.YES, ButtonType.NO);
        confirmationMessage.setTitle(HarmoniaMessagesConstants.DIRECT_MESSAGES_DELETE_CONFIRMATION_TITLE);
        confirmationMessage.setHeaderText(HarmoniaMessagesConstants.DIRECT_MESSAGES_DELETE_CONFIRMATION_HEADER);
        confirmationMessage.setContentText(HarmoniaMessagesConstants.DIRECT_MESSAGES_DELETE_CONFIRMATION_BODY);
        confirmationMessage.showAndWait();
        return confirmationMessage.getResult() == ButtonType.YES;
    }
}
