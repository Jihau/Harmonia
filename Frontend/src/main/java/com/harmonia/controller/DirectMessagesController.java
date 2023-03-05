package com.harmonia.controller;

import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.constants.HarmoniaData;
import com.harmonia.constants.HarmoniaMessagesConstants;
import com.harmonia.utils.HarmoniaDataLoader;
import com.harmonia.utils.HarmoniaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * The DirectMessagesController class is responsible for controlling the user's interactions
 * with the chat view in the Harmonia application. It communicates with the DirectMessageClient
 * and UserClient classes to send and receive messages and user information. This class also
 * handles events triggered by various buttons in the chat view,
 *
 * @author Harmonia Team
 * @version 1.0
 */
public class DirectMessagesController extends MainViewController {
    @FXML
    Pane editBox;
    @FXML
    Button confirmEditButton;
    @FXML
    Button cancelEditButton;
    @FXML
    private TextField sendMessageField;
    @FXML
    private Button sendBtn;
    @FXML
    private Button editButton;
    @FXML
    private TextField editTextField;

    public DirectMessagesController() {

    }

    /**
     * Initializes the ChatController by setting up various variables and objects.
     * Specifically, this method sets up the comparator used to sort messages, initializes
     * the currently logged-in user, creates a new DirectMessageClient and UserClient object,
     * sets the chat target ID and name, creates two observable lists for holding the conversation,
     * calls populateListView() to populate the list view with messages, and calls searchUserByUsername()
     * to populate the user search bar.
     */
    public void initialize() {
        HarmoniaDataLoader.searchUserByUsername("");
    }

    @FXML
    public void onRefreshButtonClick() {
        HarmoniaDataLoader.loadDirectMessagesByUserId(false);
    }

    /**
     * Gets the text from the message TextField tries to send it via the @link DirectMessageClient.
     * @param event
     */
    @FXML
    public void onSendBtnClick(ActionEvent event) {
        if (HarmoniaDataLoader.sendDirectMessage(sendMessageField.getText())) {
            sendMessageField.setText(HarmoniaMessagesConstants.LABEL_EMPTY_STRING);
            HarmoniaDataLoader.loadDirectMessagesByUserId(false);
        } else {
            sendMessageField.setText(HarmoniaMessagesConstants.ERROR_SENDING_MESSAGE);
        }
    }

    /**
     * assigns the selected message by getting the index from listview and getting the matching index from the DirectMessagePO list.
     * If the Message recipient ID is different from the logged user ID open the edit box. Otherwise show a "not your message"-alert.
     */
    @FXML
    public void onEditButtonClick() {
        if (HarmoniaData.SELECTED_DIRECT_MESSAGE == null) {
            HarmoniaUtils.showErrorMessage(HarmoniaMessagesConstants.DIRECT_MESSAGES_ERROR_NOT_SELECTED_TITLE,
                    HarmoniaMessagesConstants.DIRECT_MESSAGES_ERROR_NOT_SELECTED_HEADER,
                    HarmoniaMessagesConstants.DIRECT_MESSAGES_ERROR_NOT_SELECTED_BODY);
            return;
        }
        if (HarmoniaData.SELECTED_DIRECT_MESSAGE.getRecipientId() != HarmoniaConstants.LOGGED_USERS.getUserId()) {
            editBox.setVisible(true);
        } else {
            Alert notYourMessageAlert = new Alert(AlertType.ERROR);
            notYourMessageAlert.setTitle("Not your message");
            notYourMessageAlert.setHeaderText("Not your message!");
            notYourMessageAlert.setContentText("You cannot edit messages from other people!");
            notYourMessageAlert.showAndWait();
        }
    }

    /**
     * if the message TextField is not blank, send an edit request to the backend with the edited messageText. Otherwise highlight the textbox and add a prompt.
     * Afterwards update the list of messages.
     */
    @FXML
    public void onConfirmEditButtonCLick() {
        if (HarmoniaData.SELECTED_DIRECT_MESSAGE == null) {
            HarmoniaUtils.showErrorMessage(HarmoniaMessagesConstants.DIRECT_MESSAGES_ERROR_NOT_SELECTED_TITLE,
                    HarmoniaMessagesConstants.DIRECT_MESSAGES_ERROR_NOT_SELECTED_HEADER,
                    HarmoniaMessagesConstants.DIRECT_MESSAGES_ERROR_NOT_SELECTED_BODY);
            return;
        }
        if (HarmoniaDataLoader.editDirectMessage(editTextField.getText())) {
            editTextField.setStyle(HarmoniaMessagesConstants.LABEL_EMPTY_STRING);
            editBox.setVisible(false);
            editTextField.setText(HarmoniaMessagesConstants.LABEL_EMPTY_STRING);
            HarmoniaDataLoader.loadDirectMessagesByUserId(false);
        } else {
            editTextField.setPromptText(HarmoniaMessagesConstants.ERROR_EMPTY_MESSAGE);
            editTextField.setStyle("-fx-text-box-border: #B22222;");
        }
    }

    /**
     * close the editbox
     */
    @FXML
    public void onCancelButtonClick() {
        editTextField.setStyle("");
        editTextField.setText("");
        editBox.setVisible(false);
    }

    /**
     * get selected message from
     */
    @FXML
    public void onRemoveMessageButttonClick() {
        if (HarmoniaData.SELECTED_DIRECT_MESSAGE == null) {
            HarmoniaUtils.showErrorMessage(HarmoniaMessagesConstants.DIRECT_MESSAGES_ERROR_NOT_SELECTED_TITLE,
                    HarmoniaMessagesConstants.DIRECT_MESSAGES_ERROR_NOT_SELECTED_HEADER,
                    HarmoniaMessagesConstants.DIRECT_MESSAGES_ERROR_NOT_SELECTED_BODY);
            return;
        }
        if (HarmoniaData.SELECTED_DIRECT_MESSAGE.getRecipientId() != HarmoniaConstants.LOGGED_USERS.getUserId()) {
            if (HarmoniaUtils.showConfirmationMessage(HarmoniaMessagesConstants.DIRECT_MESSAGES_DELETE_CONFIRMATION_MESSAGE,
                    HarmoniaMessagesConstants.DIRECT_MESSAGES_DELETE_CONFIRMATION_TITLE,
                    HarmoniaMessagesConstants.DIRECT_MESSAGES_DELETE_CONFIRMATION_HEADER,
                    HarmoniaMessagesConstants.DIRECT_MESSAGES_DELETE_CONFIRMATION_BODY
            )) {
                if (!HarmoniaDataLoader.deleteDirectMessage()) {
                    HarmoniaUtils.showErrorMessage(HarmoniaMessagesConstants.DIRECT_MESSAGES_CANNOT_DELETE_TITLE,
                            HarmoniaMessagesConstants.DIRECT_MESSAGES_CANNOT_DELETE_HEADER_TEXT,
                            HarmoniaMessagesConstants.DIRECT_MESSAGES_CANNOT_DELETE_BODY_TEXT);
                }
            }

        } else {
            HarmoniaUtils.showErrorMessage(HarmoniaMessagesConstants.DIRECT_MESSAGES_NOT_YOURS_TITLE,
                    HarmoniaMessagesConstants.DIRECT_MESSAGES_NOT_YOURS_HEADER_TEXT,
                    HarmoniaMessagesConstants.DIRECT_MESSAGES_NOT_YOURS_BODY_TEXT);
        }
    }
}