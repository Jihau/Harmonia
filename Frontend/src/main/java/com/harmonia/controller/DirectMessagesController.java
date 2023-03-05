package com.harmonia.controller;

import com.harmonia.client.DirectMessageClient;
import com.harmonia.client.UserClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.po.MessagePO;
import com.harmonia.po.UserPO;
import com.harmonia.utils.HarmoniaDataLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import org.springframework.http.ResponseEntity;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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

    Comparator<MessagePO> comparator;
    @FXML
    Pane editBox;
    @FXML
    Button confirmEditButton;
    @FXML
    Button cancelEditButton;
    ObservableList<MessagePO> conversationObject;
    ObservableList<String> conversationString;
    private List<MessagePO> messages;
    private UserPO loggedInUser;
    private int chatTargetId;
    private String chatTargetName;
    private MessagePO selectedMessage;
    @FXML
    private TextField sendMessageField;
    @FXML
    private Button sendBtn;
    @FXML
    private Button editButton;
    @FXML
    private TextField editTextField;
    @FXML
    private ListView<String> ChatListView;
    private DirectMessageClient messageClient;
    private UserClient userClient;

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
        comparator = Comparator.comparingInt(MessagePO::getDmessageId);
        loggedInUser = HarmoniaConstants.LOGGED_USERS;
        messageClient = new DirectMessageClient();
        userClient = new UserClient();
        chatTargetId = 2;
        chatTargetName = userClient.getUserByID(chatTargetId).getUsername();
        conversationObject = FXCollections.observableArrayList();
        conversationString = FXCollections.observableArrayList();

        populateListView();
        HarmoniaDataLoader.searchUserByUsername("");
    }

    /**
     * Sends a message to the recipient specified in the message object and returns the response from the message client.
     *
     * @param message the message to be sent
     * @return the response from the message client
     */
    public ResponseEntity<?> sendMessage(MessagePO message) {
        return messageClient.addMessage(message);
    }


    protected void populateListView() {
        conversationObject.clear();
        for (MessagePO m : Objects.requireNonNull(messageClient.getMessagesByRecipientID(loggedInUser.getUserId()).getBody())) {
            if (m.getAuthorId() == chatTargetId)
                conversationObject.add(m);
        }
        for (MessagePO m : Objects.requireNonNull(messageClient.getMessagesByAuthorId(loggedInUser.getUserId()).getBody())) {
            if (m.getRecipientId() == chatTargetId) {
                conversationObject.add(m);
            }
        }
        conversationObject.sort(comparator);
        convertList();
        if (conversationString.size() > 0) {
            ChatListView.setItems(conversationString);
            ChatListView.scrollTo(ChatListView.getItems().size());
        } else {
            conversationString.add("No messages, send a message to start a conversation");
        }
    }

    @FXML
    public void onRefreshButtonClick() {
        populateListView();
    }


    @FXML
    public void onSendBtnClick(ActionEvent event) {
        MessagePO newMessage = new MessagePO();

        newMessage.setMessageText(sendMessageField.getText());
        newMessage.setAuthorId(loggedInUser.getUserId());
        newMessage.setRecipientId(chatTargetId);

        conversationString.add("Pending... " + newMessage.getMessageText());

        ResponseEntity<?> response = this.sendMessage(newMessage);
        System.out.println(response.getStatusCode());

        sendMessageField.setText("");
        populateListView();

    }

    protected void convertList() {
        conversationString.clear();
        for (MessagePO m : conversationObject) {
            if (m.getRecipientId() != loggedInUser.getUserId()) {
                conversationString.add("you: " + m.prettyString());
            } else {
                conversationString.add(chatTargetName + ": " + m.prettyString());
            }

        }
    }

    @FXML
    public void onEditButtonClick() {
        int index = ChatListView.getSelectionModel().getSelectedIndex();

        MessagePO listSelectedMessage = conversationObject.get(index);

        if (listSelectedMessage.getRecipientId() != loggedInUser.getUserId()) {
            setSelectedMessage(listSelectedMessage);
            editBox.setVisible(true);
        } else {
            Alert notYourMessageAlert = new Alert(AlertType.ERROR);
            notYourMessageAlert.setTitle("Not your message");
            notYourMessageAlert.setHeaderText("Not your message!");
            notYourMessageAlert.setContentText("You cannot edit messages from other people!");
            notYourMessageAlert.showAndWait();
        }
    }

    @FXML
    public void onConfirmEditButtonCLick() {

        MessagePO editSelectedMessage = getSelectedMessage();

        System.out.println("AuthorId: " + editSelectedMessage.getAuthorId());
        System.out.println("MessageId: " + editSelectedMessage.getDmessageId());

        if (!Objects.equals(editTextField.getText(), "")) {

            editSelectedMessage.setMessageText(editTextField.getText());
            editSelectedMessage.setAuthorId(loggedInUser.getUserId());
            editSelectedMessage.setRecipientId(chatTargetId);

            messageClient.editMessage(editSelectedMessage);

            editTextField.setStyle("");
            editBox.setVisible(false);
            editTextField.setText("");

        } else {
            editTextField.setPromptText("Please fill me before submitting!");
            editTextField.setStyle("-fx-text-box-border: #B22222;");
        }
        populateListView();
    }

    @FXML
    public void onCancelButtonClick() {
        editTextField.setStyle("");
        editTextField.setText("");
        editBox.setVisible(false);
    }

    @FXML
    public void onRemoveMessageButttonClick() {
        int index = ChatListView.getSelectionModel().getSelectedIndex();

        if (conversationObject.get(index).getRecipientId() != loggedInUser.getUserId()) {

            Alert deleteConfirmation = new Alert(AlertType.CONFIRMATION, "Delete message?", ButtonType.YES, ButtonType.NO);
            deleteConfirmation.setTitle("Delete message");
            deleteConfirmation.setHeaderText("Are you sure you want to delete this message?");
            deleteConfirmation.setContentText("Deleting a message is an irreversible action and cannot be undone.");
            deleteConfirmation.showAndWait();

            if (deleteConfirmation.getResult() == ButtonType.YES) {
                System.out.println("Attempting delete");
                MessagePO deleteMe = conversationObject.get(index);

                messageClient.removeMessage(deleteMe);
            }

        } else {
            Alert notYourMessageAlert = new Alert(AlertType.ERROR);
            notYourMessageAlert.setTitle("Not your message");
            notYourMessageAlert.setHeaderText("Can't delete message");
            notYourMessageAlert.setContentText("You can only delete messages that you've sent!");
            notYourMessageAlert.showAndWait();
        }
        populateListView();
    }

    public MessagePO getSelectedMessage() {
        return this.selectedMessage;
    }


    public void setSelectedMessage(MessagePO message) {
        this.selectedMessage = message;
    }

}