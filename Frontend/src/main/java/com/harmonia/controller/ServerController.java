package com.harmonia.controller;

import java.util.ArrayList;

import com.harmonia.client.ChannelClient;
import com.harmonia.client.PublicMessageClient;
import com.harmonia.client.ServerClient;
import com.harmonia.client.ServerMemberClient;
import com.harmonia.client.UserClient;
import com.harmonia.po.ChannelPO;
import com.harmonia.po.PublicMessagePO;
import com.harmonia.po.ServerMemberPO;
import com.harmonia.po.ServerPO;
import com.harmonia.po.UserPO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ServerController {

    private ServerPO selectedServer;
    private UserPO loggedInUser;
    private ChannelPO selectedChannel;
    private PublicMessagePO selectedMessage;
    
    private UserClient userClient;
    private ServerClient serverClient;
    private ServerMemberClient serverMemberClient;
    private PublicMessageClient publicMessageClient;
    private ChannelClient channelClient;
    


    @FXML
    private GridPane LeftSidebar;

    @FXML
    private ListView<?> PublicMessages;

    @FXML
    private ListView<?> channelList;

    @FXML
    private ListView<?> memberList;

    @FXML
    private Label ServerName;

    @FXML
    private Label blindLabel;

    @FXML
    private Button cancelEditButton;

    @FXML
    private Pane channelBlind;

    @FXML
    private Button confirmEditButton;

    @FXML
    private Pane editBox;

    @FXML
    private AnchorPane editBoxWrapper;

    @FXML
    private Button editMessageButton;

    @FXML
    private TextField editTextField;

    @FXML
    private Button refreshButton;

    @FXML
    private Button removeMessageButton;

    @FXML
    private AnchorPane root;

    @FXML
    private Button sendBtn;

    @FXML
    private TextField sendMessageField;

    ObservableList<PublicMessagePO> publicMessagesList;
    ObservableList<String> publicStringsList;

    ObservableList<ChannelPO> channelObjectList;
    ObservableList<String> channelStringList;

    ObservableList<ServerMemberPO> userObjectList;
    ObservableList<String> userStringList;

    public void initialize() {

        this.userClient = new UserClient();
        this.serverClient = new ServerClient();
        this.publicMessageClient = new PublicMessageClient();
        this.serverMemberClient = new ServerMemberClient();
        this.channelClient = new ChannelClient();
        
        loggedInUser = new UserPO();
        loggedInUser.setUserId(1);

        selectedChannel = channelClient.listAllChannels()[0]; 


        selectedServer = new ServerPO();
        selectedServer.setServerId(1);

        publicMessagesList = FXCollections.observableArrayList();
        publicStringsList = FXCollections.observableArrayList();

        channelObjectList =  FXCollections.observableArrayList();
        channelStringList = FXCollections.observableArrayList();

        userObjectList = FXCollections.observableArrayList();
        userStringList = FXCollections.observableArrayList();


        getObjects();

        populateMessageList();
        populateUserList();
        populateChannelList();

    }

    public void populateChannelList() {
        channelObjectList.clear();

        for (ChannelPO channel : channelClient.listAllChannels().clone()) {
            if (channel.getServerId()==selectedServer.getServerId()) {
                channelObjectList.add(channel);
            }
        }

        for (ChannelPO channel : channelObjectList)  {
            channelStringList.add("#" + channel.getChannelName());
        }

        
    }

    public void populateUserList() {

        userObjectList.clear();
        userStringList.clear();

        for (ServerMemberPO member : serverMemberClient.listMembersByServerId((long)selectedServer.getServerId())) {
            userObjectList.add(member);
        }

        for (ServerMemberPO member : userObjectList) {
            userStringList.add(member.getNickName());
        }
        
    }

    public void populateMessageList() {
        publicMessagesList.clear();
        publicStringsList.clear();
        PublicMessagePO[] messageArray = publicMessageClient.getAllMessages();

        for (PublicMessagePO message : messageArray) {
            if (message.getChannelId()==selectedChannel.getChannelId()) { publicMessagesList.add(message); }
        }

        for (PublicMessagePO m: publicMessagesList) {
            String authorName = "Unknown";

            for (ServerMemberPO member : userObjectList) {
                if (m.getAuthorId() == member.getUserId()) { authorName = member.getUsername(); }
            }

            publicStringsList.add(  authorName + ": " + m.getMessageText() );

        }
    }


    @FXML
    void onCancelButtonClick(ActionEvent event) {
        editTextField.setStyle("");
        editTextField.setText("");
        editBox.setVisible(false);
    }

    @FXML
    void onConfirmEditButtonCLick(ActionEvent event) {
        if (editTextField.getText()!="") {
            PublicMessagePO editedMessage = this.selectedMessage;
            editedMessage.setMessageText(editTextField.getText());

            publicMessageClient.editPublicMessage(editedMessage);

            editTextField.setStyle("");
            editBox.setVisible(false);
            editTextField.setText("");
        } else {
                editTextField.setPromptText("Please fill me before submitting!");
                editTextField.setStyle("-fx-text-box-border: #B22222;");
            }
            populateMessageList();
        }

    @FXML
    void onEditButtonClick(ActionEvent event) {
        int index = PublicMessages.getSelectionModel().getSelectedIndex();

        PublicMessagePO listSelectedMessage = publicMessagesList.get(index);

        if (listSelectedMessage.getAuthorId()==loggedInUser.getUserId()) {
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

    private void setSelectedMessage(PublicMessagePO listSelectedMessage) {
        this.selectedMessage = listSelectedMessage;
    }

    @FXML
    void onRefreshButtonClick(ActionEvent event) {
        populateMessageList();
    }

    @FXML
    void onRemoveMessageButttonClick(ActionEvent event) {
        int index = PublicMessages.getSelectionModel().getSelectedIndex();
        if (publicMessagesList.get(index).getAuthorId()==loggedInUser.getUserId()) { 
            
         }
    }

    @FXML
    void onSendBtnClick(ActionEvent event) {
        String message = sendMessageField.getText();
        if (message!="") {
            PublicMessagePO newMessage = new PublicMessagePO();
            newMessage.setAuthorId((long) loggedInUser.getUserId());
            newMessage.setChannelId((long)selectedChannel.getChannelId());
            newMessage.setMessageText(sendMessageField.getText());

        }
    }

    public void getObjects() {
        channelObjectList.clear();
        userObjectList.clear();
        publicMessagesList.clear();


        this.channelObjectList.addAll(channelClient.listAllChannels());
        this.userObjectList.addAll(serverMemberClient.listMembersByServerId((long)selectedServer.getServerId()));
        this.publicMessagesList.addAll(publicMessageClient.getAllMessages());
    }

}
