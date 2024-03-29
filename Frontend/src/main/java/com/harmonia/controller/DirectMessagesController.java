package com.harmonia.controller;

import javax.swing.text.html.ListView;

import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.constants.HarmoniaData;
import com.harmonia.utils.HarmoniaDataLoader;
import com.harmonia.utils.HarmoniaUtils;
import com.harmonia.view.MessagesListView;
import com.harmonia.view.UsersListView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
    public UsersListView listUsersListView;
    public Pane listUsersPanel;
    public ScrollPane listUsersScrollPanel;
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
    @FXML
    private MessagesListView ChatListView;
    @FXML
    private Label userListLabel;

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
        userListLabel.setText(HarmoniaConstants.textconstants.usersText);

    }

    /**
     * function called when clicking the refresh button.
     */
    @FXML
    public void onRefreshButtonClick() {
        HarmoniaDataLoader.loadDirectMessagesByUserId(false);
        scrollToBottom();
    }

    /**
     * Gets the text from the message TextField tries to send it via the @link DirectMessageClient.
     * @param event the event that triggered the method call
     */
    @FXML
    public void onSendBtnClick(ActionEvent event) {
        if (HarmoniaDataLoader.sendDirectMessage(sendMessageField.getText())) {
            sendMessageField.setText(HarmoniaConstants.messages.LABEL_EMPTY_STRING);
            HarmoniaDataLoader.loadDirectMessagesByUserId(false);
            scrollToBottom();
        } else {
            sendMessageField.setText(HarmoniaConstants.messages.ERROR_SENDING_MESSAGE);
        }
    }

    /**
     * assigns the selected message by getting the index from listview and getting the matching index from the DirectMessagePO list.
     * If the Message recipient ID is different from the logged user ID open the edit box. Otherwise show a "not your message"-alert.
     */
    @FXML
    public void onEditButtonClick() {
        if (HarmoniaData.SELECTED_DIRECT_MESSAGE == null) {
            HarmoniaUtils.showErrorMessage(HarmoniaConstants.messages.DIRECT_MESSAGES_ERROR_NOT_SELECTED_TITLE,
                    HarmoniaConstants.messages.DIRECT_MESSAGES_ERROR_NOT_SELECTED_HEADER,
                    HarmoniaConstants.messages.DIRECT_MESSAGES_ERROR_NOT_SELECTED_BODY);
            return;
        }
        if (HarmoniaData.SELECTED_DIRECT_MESSAGE.getRecipientId() != HarmoniaConstants.LOGGED_USERS.getUserId()) {
            editBox.setVisible(true);
        } else {
            Alert notYourMessageAlert = new Alert(AlertType.ERROR);
            notYourMessageAlert.setTitle(HarmoniaConstants.messages.DIRECT_MESSAGES_NOT_YOURS_TITLE);
            notYourMessageAlert.setHeaderText(HarmoniaConstants.messages.DIRECT_MESSAGES_ERROR_NOT_SELECTED_HEADER);
            notYourMessageAlert.setContentText(HarmoniaConstants.messages.DIRECT_MESSAGES_NOT_YOURS_BODY_TEXT);
            notYourMessageAlert.showAndWait();
        }
    }

    /**
     * if the message TextField is not blank, send an edit request to the backend with the edited messageText. Otherwise highlight the textbox and add a prompt.
     * Afterward update the list of messages.
     */
    @FXML
    public void onConfirmEditButtonClick() {
        if (HarmoniaData.SELECTED_DIRECT_MESSAGE == null) {
            HarmoniaUtils.showErrorMessage(HarmoniaConstants.messages.DIRECT_MESSAGES_ERROR_NOT_SELECTED_TITLE,
                    HarmoniaConstants.messages.DIRECT_MESSAGES_ERROR_NOT_SELECTED_HEADER,
                    HarmoniaConstants.messages.DIRECT_MESSAGES_ERROR_NOT_SELECTED_BODY);
            return;
        }
        if (HarmoniaDataLoader.editDirectMessage(editTextField.getText())) {
            editTextField.setStyle(HarmoniaConstants.messages.LABEL_EMPTY_STRING);
            editBox.setVisible(false);
            editTextField.setText(HarmoniaConstants.messages.LABEL_EMPTY_STRING);
            HarmoniaDataLoader.loadDirectMessagesByUserId(false);
        } else {
            editTextField.setPromptText(HarmoniaConstants.messages.ERROR_EMPTY_MESSAGE);
            editTextField.setStyle("-fx-text-box-border: #B22222;");
        }
    }

    /**
     * close the editor
     */
    @FXML
    public void onCancelButtonClick() {
        editTextField.setStyle("");
        editTextField.setText("");
        editBox.setVisible(false);
    }

    /**
     * Prompts the user if they want to delete their message. shows an error if the user tries to delete a message they didn't send.
     */
    @FXML
    public void onRemoveMessageButttonClick() {
        if (HarmoniaData.SELECTED_DIRECT_MESSAGE == null) {
            HarmoniaUtils.showErrorMessage(HarmoniaConstants.messages.DIRECT_MESSAGES_ERROR_NOT_SELECTED_TITLE,
                    HarmoniaConstants.messages.DIRECT_MESSAGES_ERROR_NOT_SELECTED_HEADER,
                    HarmoniaConstants.messages.DIRECT_MESSAGES_ERROR_NOT_SELECTED_BODY);
            return;
        }
        if (HarmoniaData.SELECTED_DIRECT_MESSAGE.getRecipientId() != HarmoniaConstants.LOGGED_USERS.getUserId()) {
            if (HarmoniaUtils.showConfirmationMessage(HarmoniaConstants.messages.DIRECT_MESSAGES_DELETE_CONFIRMATION_MESSAGE,
                    HarmoniaConstants.messages.DIRECT_MESSAGES_DELETE_CONFIRMATION_TITLE,
                    HarmoniaConstants.messages.DIRECT_MESSAGES_DELETE_CONFIRMATION_HEADER,
                    HarmoniaConstants.messages.DIRECT_MESSAGES_DELETE_CONFIRMATION_BODY
            )) {
                if (!HarmoniaDataLoader.deleteDirectMessage()) {
                    HarmoniaUtils.showErrorMessage(HarmoniaConstants.messages.DIRECT_MESSAGES_CANNOT_DELETE_TITLE,
                            HarmoniaConstants.messages.DIRECT_MESSAGES_CANNOT_DELETE_HEADER_TEXT,
                            HarmoniaConstants.messages.DIRECT_MESSAGES_CANNOT_DELETE_BODY_TEXT);
                }
            }

        } else {
            HarmoniaUtils.showErrorMessage(HarmoniaConstants.messages.DIRECT_MESSAGES_NOT_YOURS_TITLE,
                    HarmoniaConstants.messages.DIRECT_MESSAGES_NOT_YOURS_HEADER_TEXT,
                    HarmoniaConstants.messages.DIRECT_MESSAGES_NOT_YOURS_BODY_TEXT);
        }
    }
    /**
     * Scrolls the message list to the bottom.
     */
    @FXML
    private void scrollToBottom() {
        ChatListView.scrollTo(HarmoniaData.DIRECT_MESSAGES_LIST.size());
    }
}