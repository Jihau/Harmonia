package com.harmonia.controller;

import com.harmonia.client.PublicMessageClient;
import com.harmonia.client.ServerClient;
import com.harmonia.client.ServerMemberClient;
import com.harmonia.client.UserClient;
import com.harmonia.po.ServerPO;
import com.harmonia.po.UserPO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ServerController {

    private ServerPO selectedServer;
    private UserPO loggedInUser;
    
    private UserClient userClient;
    private ServerClient serverClient;
    private ServerMemberClient serverMemberClient;
    private PublicMessageClient publicMessageClient;
    


    @FXML
    private GridPane LeftSidebar;

    @FXML
    private ListView<?> PublicMessages;

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

    @FXML
    void onCancelButtonClick(ActionEvent event) {

    }

    @FXML
    void onConfirmEditButtonCLick(ActionEvent event) {

    }

    @FXML
    void onEditButtonClick(ActionEvent event) {

    }

    @FXML
    void onRefreshButtonClick(ActionEvent event) {

    }

    @FXML
    void onRemoveMessageButttonClick(ActionEvent event) {

    }

    @FXML
    void onSendBtnClick(ActionEvent event) {

    }

}
