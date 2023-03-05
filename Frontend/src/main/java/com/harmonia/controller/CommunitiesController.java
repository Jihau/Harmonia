package com.harmonia.controller;

import com.harmonia.HarmoniaApplication;
import com.harmonia.client.ServerClient;
import com.harmonia.client.ServerMemberClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.po.ServerMemberPO;
import com.harmonia.po.ServerPO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CommunitiesController extends MainViewController {

    private ServerMemberClient memberClient;
    private ServerClient serverClient;
    private ArrayList<ServerPO> myServers;

    @FXML
    private ListView<String> serverList;


    /**
     * Initializes the CommunitiesController by setting up the necessary variables and loading any existing servers.
     */
    public void initialize() {
        this.serverClient = new ServerClient();
        this.memberClient = new ServerMemberClient();
        this.myServers = new ArrayList<>();

        populateServerListView();

        serverList.setOnMouseClicked(event -> {
            if (event.getTarget() == null) {
                System.out.println("Null found");
            } else {
                ServerPO selected = myServers.get(serverList.getSelectionModel().getSelectedIndex());

                sendToServer(event, selected);
            }
        });
    }

    /**
     * Lists the servers that user belongs to on the existing ListView called serverList
     */
    private void populateServerListView() {
        ServerMemberPO[] servers = memberClient.listServersByMemberId(HarmoniaConstants.LOGGED_USERS.getUserId());
        for (ServerMemberPO server : servers) {
            myServers.add(serverClient.getServerById(server.getServerId()));
            serverList.getItems().add(server.getServerName());
        }
    }

    /**
     * Handles the event when the servers button is clicked, which loads the view of the server that user selected.
     *
     * @param event    the event that triggered this method
     * @param sendHere sets the server variable in @link ServerController
     */
    private void sendToServer(MouseEvent event, ServerPO sendHere) {

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("server-view.fxml"));
            loader.setController(new ServerController(sendHere));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle(sendHere.getServerName());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
