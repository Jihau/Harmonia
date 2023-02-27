package com.harmonia.controller;

import java.io.IOException;
import java.util.*;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.DirectMessageClient;
import com.harmonia.po.MessagePO;
import com.harmonia.view.ChatView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private ListView ChatListView;

    private DirectMessageClient messageClient;

    ObservableList<MessagePO> conversationObject = FXCollections.observableArrayList();
    ObservableList<String> conversationString = FXCollections.observableArrayList();


    public void initialize() {
        this.messages = new ArrayList<>();
        this.messageClient = new DirectMessageClient();
    }

    public ChatController(){

    }

    public void sendMessage(MessagePO message) {
        // Add the message to the list and update the view
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
        for(MessagePO m : messageClient.getAllMessages().getBody()){
            System.out.println(m);
            conversationObject.add(m);
        }
        convertList();
        ChatListView.setItems(conversationString);
    }

    @FXML
    public void onSendBtnClick(){
        populateListView();
    }

    protected void convertList(){
        conversationString.clear();
        for (MessagePO m : conversationObject){
            conversationString.add(m.toString());
        }
        //System.out.println(conversationObject);
    }
    protected void drawListView(){
        ChatListView.setItems(conversationString);
    }
}