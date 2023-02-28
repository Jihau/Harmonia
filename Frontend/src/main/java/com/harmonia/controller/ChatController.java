package com.harmonia.controller;

import java.io.IOException;
import java.util.*;

import org.springframework.http.ResponseEntity;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.DirectMessageClient;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ChatController {
    private ChatView view;
    private List<MessagePO> messages;
    private UserPO loggedInUser;
    private int chatTarget;
    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     */
    @FXML
    private Button fmFriendsBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     */
    @FXML
    private Button fmSettingsBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     */
    @FXML
    private Button fmProfileBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
     */
    @FXML
    private Button fmHomePageBtn;

    /**
     navigation button for nav menu
     letter combination before name indicates in what view the button is from
     h=harmonia-view
     fm=messages-view
     p=profile-view
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

    ObservableList<MessagePO> conversationObject;
    ObservableList<String> conversationString;

    @FXML
    ObservableList<MessagePO> dummyMessages = FXCollections.observableArrayList();
    
    @FXML
    ObservableList<String> dummyTexts = FXCollections.observableArrayList();

    public void initialize() {
        // dummy data 
        this.loggedInUser = new UserPO();
        loggedInUser.setUserId(1);
        chatTarget = 2;


        conversationObject = FXCollections.observableArrayList();
        conversationString = FXCollections.observableArrayList();
        messages = new ArrayList<>();
        messageClient = new DirectMessageClient();

        MessagePO newMessage1 = new MessagePO();
        newMessage1.setdmessageId(1);
        newMessage1.setText("Boi i love quake");
        newMessage1.setTimestamp("1");

        dummyMessages.add(newMessage1);

        MessagePO newMessage2 = new MessagePO();
        newMessage2.setdmessageId(2);
        newMessage2.setText("i LOOOVE afps");
        newMessage2.setTimestamp("2");
        dummyMessages.add(newMessage2);

        for (int i=3; i<30;i++) {
            dummyMessages.add(newMessage2);
        }
        
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
    protected void populateListView(){
        conversationObject.clear();
        for(MessagePO m : messageClient.getAllMessages()){
            System.out.println(m.getText());
            conversationObject.add(m);
        }
        convertList();
        ChatListView.setItems(dummyTexts);

    }

    @FXML
    public void onSendBtnClick(){
        MessagePO newMessage = new MessagePO();
        newMessage.setText(sendMessageField.getText());
        newMessage.setSenderId(loggedInUser.getUserId());
        newMessage.setReceiverId(chatTarget);

        dummyTexts.add(newMessage.getText());
        
        ResponseEntity<?> response = this.sendMessage(newMessage);
        System.out.println(response.getStatusCode());
    }

    protected void convertList(){
        conversationString.clear();
        for (MessagePO m : dummyMessages){
            dummyTexts.add(m.prettyString());
        }
        //System.out.println(conversationObject);
    }
    protected void drawListView(){
        ChatListView.setItems(dummyTexts);
    }
}