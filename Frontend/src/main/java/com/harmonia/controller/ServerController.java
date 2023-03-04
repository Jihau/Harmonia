package com.harmonia.controller;

import com.harmonia.client.ChannelClient;
import com.harmonia.client.PublicMessageClient;
import com.harmonia.client.ServerClient;
import com.harmonia.client.ServerMemberClient;
import com.harmonia.client.UserClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.po.ChannelPO;
import com.harmonia.po.PublicMessagePO;
import com.harmonia.po.ServerMemberPO;
import com.harmonia.po.ServerPO;
import com.harmonia.po.UserPO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
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
    private ListView<String> PublicMessagesList;

    @FXML
    private ListView<ChannelPO> channelList;

    @FXML
    private ListView<String> memberList;

    @FXML
    private Label ServerName;

    @FXML
    private Label serverCategory;

    @FXML
    private Label serverMemberCount;

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

    ObservableList<PublicMessagePO> PMObjectList;
    ObservableList<String> PMStringList;

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
        
        loggedInUser = HarmoniaConstants.LOGGED_USERS;

        selectedChannel = channelClient.listAllChannels()[0]; 


        // get server from event later 
        selectedServer = new ServerPO();

        selectedServer.setServerId(1);
        selectedServer.setServerCategory("Test");
        selectedServer.setServerName("Server test1");
        selectedServer.setOwnerId(1);

        PMObjectList = FXCollections.observableArrayList();
        PMStringList = FXCollections.observableArrayList();

        channelObjectList =  FXCollections.observableArrayList();
        channelStringList = FXCollections.observableArrayList();

        userObjectList = FXCollections.observableArrayList();
        userStringList = FXCollections.observableArrayList();


        getObjects();
        setServerInfo();

        populateUserList();
        populateChannelList();

        channelList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getTarget()==null) {
                    System.out.println("Null found");
                } else {
                    System.out.println("Deleting blind");
                    channelBlind.getChildren().clear();
                    root.getChildren().remove(channelBlind);
                    ChannelPO selected = channelObjectList.get(channelList.getSelectionModel().getSelectedIndex());
                    selectedChannel = selected;
                    System.out.println(selected);
                    populateMessageList();
                }
            }
        });
    }

    public void populateChannelList() {
        channelObjectList.clear();
        channelStringList.clear();
        for (ChannelPO channel : channelClient.listAllChannels().clone()) {
            if (channel.getServerId()==selectedServer.getServerId()) {
                channelObjectList.add(channel);
            }
        }
        for (ChannelPO channel : channelObjectList)  {
            channelStringList.add("#" + channel.getChannelName());
        }
        channelList.getItems().addAll(channelObjectList);
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
        memberList.setItems(userStringList);
    }

    public void populateMessageList() {
        PMObjectList.clear();
        PMStringList.clear();
        PublicMessagePO[] messageArray = publicMessageClient.getAllMessages();
        
        for (PublicMessagePO message : messageArray) {
            if (message.getChannelId()==selectedChannel.getChannelId()) { PMObjectList.add(message); }
        }
        if (PMObjectList.size()>0) {
            for (PublicMessagePO m: PMObjectList) {
                String authorName = "Unknown";

                for (ServerMemberPO member : userObjectList) {
                    if (m.getAuthorId() == member.getUserId()) {
                            authorName = member.getNickName();
                            System.out.println("Author found");
                        }
                }

                PMStringList.add(  authorName + ": " + m.getMessageText() );
            }
        } else {
            PMStringList.add("No messages on channel yet.");
        }
            PublicMessagesList.setItems(PMStringList);
        
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
        int index = PublicMessagesList.getSelectionModel().getSelectedIndex();

        PublicMessagePO listSelectedMessage = PMObjectList.get(index);

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
        int index = PublicMessagesList.getSelectionModel().getSelectedIndex();
        
        if (PMObjectList.get(index).getAuthorId()==loggedInUser.getUserId()) {
            publicMessageClient.removeMessage(selectedMessage.getPmessageId());
        }
        populateMessageList();
    }

    @FXML
    void onSendBtnClick(ActionEvent event) {
        String message = sendMessageField.getText();
        if (message!="") {
            PublicMessagePO newMessage = new PublicMessagePO();
            newMessage.setAuthorId((long) loggedInUser.getUserId());
            newMessage.setChannelId((long) selectedChannel.getChannelId());
            newMessage.setMessageText(sendMessageField.getText());

            publicMessageClient.sendPublicMessage(newMessage);
            sendMessageField.setText("");
        }
        populateMessageList();
    }

    public void getObjects() {
        channelObjectList.clear();
        userObjectList.clear();
        PMObjectList.clear();

        this.channelObjectList.addAll(channelClient.listAllChannels());
        this.userObjectList.addAll(serverMemberClient.listMembersByServerId((long)selectedServer.getServerId()));
        this.PMObjectList.addAll(publicMessageClient.getAllMessages());
    }

    public void setServerInfo() {
        ServerName.setText(selectedServer.getServerName());
        serverCategory.setText(selectedServer.getServerCategory());
        serverMemberCount.setText(String.valueOf(userObjectList.size()));
    }

    @FXML
    public void onChannelListClick(ActionEvent event) {
        System.out.println("Channel list clicked");
    }
}
