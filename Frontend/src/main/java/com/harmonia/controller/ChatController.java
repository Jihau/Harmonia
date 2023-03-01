package com.harmonia.controller;

import java.io.IOException;
import java.lang.annotation.Target;
import java.util.*;

import org.springframework.http.ResponseEntity;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.DirectMessageClient;
import com.harmonia.client.UserClient;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChatController {
    private ChatView view;
    private List<MessagePO> messages;
    private UserPO loggedInUser;
    private int chatTargetId;
    private String chatTargetName;
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
    private TextField sendMessageField;

    @FXML
    private Button sendBtn;

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
            conversationObject.add(m);
        }
        convertList();
        ChatListView.setItems(conversationString);

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
            if (m.getauthorId()==loggedInUser.getUserId()) {
                conversationString.add("you: " + m.prettyString());
            } else {
                conversationString.add(chatTargetName + ": " + m.prettyString());
            }
        }
    }

    protected void drawListView(){
        ChatListView.setItems(conversationString);
    }
}