package com.harmonia.utils;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.ContentfulClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.constants.HarmoniaData;
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
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.util.Date;

/**
 * A utility class containing static methods that can be used across the application.
 *
 * @author Harmonia team
 * @version 2.0
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
        headers.set(HarmoniaConstants.API_KEY_HEADER_NAME, HarmoniaData.KEY_FOR_HARMONIA_BACK_END);
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
        Alert confirmationMessage = new Alert(Alert.AlertType.CONFIRMATION, HarmoniaConstants.messages.DIRECT_MESSAGES_DELETE_CONFIRMATION_MESSAGE, ButtonType.YES, ButtonType.NO);
        confirmationMessage.setTitle(HarmoniaConstants.messages.DIRECT_MESSAGES_DELETE_CONFIRMATION_TITLE);
        confirmationMessage.setHeaderText(HarmoniaConstants.messages.DIRECT_MESSAGES_DELETE_CONFIRMATION_HEADER);
        confirmationMessage.setContentText(HarmoniaConstants.messages.DIRECT_MESSAGES_DELETE_CONFIRMATION_BODY);
        confirmationMessage.showAndWait();
        return confirmationMessage.getResult() == ButtonType.YES;
    }

    public static void generateBackEndKey() {
        HarmoniaData.KEY_FOR_HARMONIA_BACK_END = BCrypt.hashpw(HarmoniaConstants.KEYWORD_GENERATE_KEY, BCrypt.gensalt());
    }

    /**
     * Gets localized resources from contentful API for the given language.
     * @param language desired language for resources.
     */
    public static void loadLocalizedResources(String language) {
        ContentfulClient.loadLabels(language);
    }

    /**
     * Gets a matching string for the indentifier.
     * @param identifier key to look for.
     * @param defaultText default if lookup is unsuccessful.
     * @return String for a label
     */
    public static String getLabelForIdentifier(String identifier, String defaultText) {
        return HarmoniaMessagesConstants.CONTENTFUL_RESOURCE.getField(identifier) == null ? defaultText : HarmoniaMessagesConstants.CONTENTFUL_RESOURCE.getField(identifier).toString();
    }

    /**
     * Loads contentful resources for the selected locale. uses system locale if application locale isn't set.
     */
    public static void loadContentfulLabels() {
        if (HarmoniaConstants.selectedLocale == null) {
            loadLocalizedResources(System.getProperty("user.language"));
        } else {
            loadLocalizedResources(HarmoniaConstants.selectedLocale.getVariant());
        }
    }
}
