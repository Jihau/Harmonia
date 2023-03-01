package com.harmonia.controller;

import java.io.IOException;
import java.lang.annotation.Target;
import java.util.*;

import org.springframework.http.ResponseEntity;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.DirectMessageClient;
import com.harmonia.client.UserClient;
import com.harmonia.model.Message;
import com.harmonia.po.MessagePO;
import com.harmonia.po.UserPO;
import com.harmonia.view.ChatView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChatController {

    private ChatView view;
    private List<MessagePO> messages;
    private UserPO loggedInUser;
    private int chatTargetId;
    private String chatTargetName;
    private MessagePO selectedMessage;
    
    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=usersettings-view
     mc=mycommunities-view
     */
    @FXML
    private Button fmFriendsBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=usersettings-view
     mc=mycommunities-view
     */
    @FXML
    private Button fmSettingsBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=usersettings-view
     mc=mycommunities-view
     */
    @FXML
    private Button fmProfileBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=usersettings-view
     mc=mycommunities-view
     */
    @FXML
    private Button fmHomePageBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     s=usersettings-view
     mc=mycommunities-view
     */
    @FXML
    private Button fmCommunityBtn;

    @FXML
    private Button logoutButton;

    @FXML
    private TextField sendMessageField;

  

    @FXML
    private Button sendBtn;

    @FXML
    private Button editButton;

    @FXML
    Pane editBox;

    @FXML
    private TextField editTextField;

    @FXML
    Button confirmEditButton;

    @FXML
    Button cancelEditButton;

    @FXML
    private ListView<String> ChatListView;

    private DirectMessageClient messageClient;

    private UserClient userClient;

    ObservableList<MessagePO> conversationObject;
    ObservableList<String> conversationString;

    public void initialize() {
        messageClient = new DirectMessageClient();

        userClient = new UserClient();
        this.loggedInUser = new UserPO();
        loggedInUser.setUserId(1);
        chatTargetId = 2;
        chatTargetName = userClient.getUserByID(chatTargetId).getUsername();

        conversationObject = FXCollections.observableArrayList();
        conversationString = FXCollections.observableArrayList();
        
        populateListView();
    }

    public ChatController(){

    }

    public ResponseEntity<?> sendMessage(MessagePO message) {
        System.out.println(message);
        return messageClient.addMessage(message);
    } 
    

    @FXML
    protected void onfmHomePageBtnClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("harmonia-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) fmHomePageBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign in to Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void onfmProfileBtnClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("profile-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) fmProfileBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign in to Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void onfmSettingsBtnClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("usersettings-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) fmSettingsBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign in to Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    protected void populateListView(){

        conversationObject.clear();

        for(MessagePO m : messageClient.getAllMessages()){
            System.out.println(m.getMessageText());
            System.out.println(m.getRecipientId());
            conversationObject.add(m);
        }
        convertList();
        ChatListView.setItems(conversationString);
        ChatListView.scrollTo(ChatListView.getItems().size());
    }

    @FXML
    public void onRefreshButtonClick() {
        populateListView();
    }


    @FXML
    public void onSendBtnClick(){
        MessagePO newMessage = new MessagePO();

        newMessage.setMessageText(sendMessageField.getText());
        newMessage.setauthorId(loggedInUser.getUserId());
        newMessage.setRecipientId(chatTargetId);

        conversationString.add("Pending... "  + newMessage.getMessageText());
        
        ResponseEntity<?> response = this.sendMessage(newMessage);
        System.out.println(response.getStatusCode());

        populateListView();
        
    }

    protected void convertList(){
        conversationString.clear();
        for (MessagePO m : conversationObject){
            if (m.getRecipientId()!=loggedInUser.getUserId()) {
                conversationString.add("you: " + m.prettyString());
            } else {
                conversationString.add(chatTargetName + ": " + m.prettyString());
            }
        }
    }

    public void logoutOnButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource("login-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign in to Harmonia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void drawListView(){
        ChatListView.setItems(conversationString);
    }

    @FXML
    public void onEditButtonClick() {
        int index = ChatListView.getSelectionModel().getSelectedIndex();

        MessagePO listSelectedMessage = conversationObject.get(index);

        System.out.println(listSelectedMessage.getMessageText());
        System.out.println(ChatListView.getSelectionModel().getSelectedItem());
        if (listSelectedMessage.getRecipientId()!=loggedInUser.getUserId()) {
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

        System.out.println("AuthorId: " + editSelectedMessage.getauthorId());
        System.out.println("MessageId: " + editSelectedMessage.getDmessageId());

        if (editTextField.getText()!="") {
        
        System.out.println(editTextField.getText());

        editSelectedMessage.setMessageText(editTextField.getText());

        System.out.println(editSelectedMessage.getDmessageId());
        System.out.println(selectedMessage.getTimestamp());
        System.out.println(selectedMessage.getMessageText());

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

    public MessagePO getSelectedMessage() {
        return this.selectedMessage;
    }

    public void setSelectedMessage(MessagePO message) {
        this.selectedMessage = message;
    }

}