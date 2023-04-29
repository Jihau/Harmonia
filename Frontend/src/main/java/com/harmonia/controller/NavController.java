package com.harmonia.controller;

import com.harmonia.HarmoniaApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import com.harmonia.po.NavPO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class NavController extends MainViewController {
    @FXML
    private ListView<String> navMenuListView;
    ObservableList<String> navPOObservableList;

    Image homeImg = new Image(getClass().getResourceAsStream("/com/harmonia/img/homeIconActive.png"));
    Image serversImg = new Image(getClass().getResourceAsStream("/com/harmonia/img/communitiesIconActive.png"));
    Image dmImg = new Image(getClass().getResourceAsStream("/com/harmonia/img/messagesActive.png"));
    Image profileImg = new Image(getClass().getResourceAsStream("/com/harmonia/img/profileIconActive.png"));
    Image settingsImg = new Image(getClass().getResourceAsStream("/com/harmonia/img/settingsIconActive.png"));

    NavPO home = new NavPO(0, "Home", homeImg);
    NavPO servers = new NavPO(1, "Servers", serversImg);
    NavPO dm = new NavPO(2, "Direct Messages", dmImg);
    NavPO profile = new NavPO(3, "Profile", profileImg);
    NavPO settings = new NavPO(4, "Settings", settingsImg);

    public NavController() throws IOException {
    }

    public void populateNavbar(){
        navPOObservableList = FXCollections.observableArrayList();
        navPOObservableList.addAll(
                home.getNavName(), servers.getNavName(), dm.getNavName(), profile.getNavName(), settings.getNavName()
        );
        navMenuListView.setItems(navPOObservableList);
        navMenuListView.setCellFactory(param -> new ListCell<String>() {
            private final ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (name.equals(home.getNavName())) {
                        imageView.setFitHeight(23.0);
                        imageView.setFitWidth(23.0);
                        imageView.setImage(home.getImage());
                    }
                    else if (name.equals(servers.getNavName())) {
                        imageView.setFitHeight(23.0);
                        imageView.setFitWidth(23.0);
                        imageView.setImage(servers.getImage());
                    }
                    else if (name.equals(dm.getNavName())) {
                        imageView.setFitHeight(23.0);
                        imageView.setFitWidth(23.0);
                        imageView.setImage(dm.getImage());
                    }
                    else if (name.equals(profile.getNavName())){
                        imageView.setFitHeight(23.0);
                        imageView.setFitWidth(23.0);
                        imageView.setImage(profile.getImage());
                    }
                    else if (name.equals(settings.getNavName())) {
                        imageView.setFitHeight(23.0);
                        imageView.setFitWidth(23.0);
                        imageView.setImage(settings.getImage());
                    }
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });
    }

    public void navActions(){
        navMenuListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(navMenuListView.getSelectionModel().getSelectedIndex() == 0){
                    System.out.println("Home clicked");
                    try {
                        FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("harmonia-view.fxml"));
                        Stage stage = (Stage) navMenuListView.getScene().getWindow();
                        Scene scene = new Scene(loader.load(), 1280, 720);
                        stage.setScene(scene);
                        stage.setTitle("Register to Harmonia");
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(navMenuListView.getSelectionModel().getSelectedIndex() == 1){
                    System.out.println("Servers clicked");
                    try {
                        FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("mycommunities-view.fxml"));
                        Stage stage = (Stage) navMenuListView.getScene().getWindow();
                        Scene scene = new Scene(loader.load(), 1280, 720);
                        stage.setScene(scene);
                        stage.setTitle("Harmonia");
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(navMenuListView.getSelectionModel().getSelectedIndex() == 2){
                    System.out.println("DMs clicked");
                    try {
                        FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("messages-view.fxml"));
                        Stage stage = (Stage) navMenuListView.getScene().getWindow();
                        Scene scene = new Scene(loader.load(), 1280, 720);
                        stage.setScene(scene);
                        stage.setTitle("Register to Harmonia");
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(navMenuListView.getSelectionModel().getSelectedIndex() == 3){
                    System.out.println("Profile clicked");
                    try {
                        FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("profile-view.fxml"));
                        Stage stage = (Stage) navMenuListView.getScene().getWindow();
                        Scene scene = new Scene(loader.load(), 1280, 720);
                        stage.setScene(scene);
                        stage.setTitle("Register to Harmonia");
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(navMenuListView.getSelectionModel().getSelectedIndex() == 4){
                    System.out.println("Settings clicked");
                    try {
                        FXMLLoader loader = new FXMLLoader(HarmoniaApplication.class.getResource("usersettings-view.fxml"));
                        Stage stage = (Stage) navMenuListView.getScene().getWindow();
                        Scene scene = new Scene(loader.load(), 1280, 720);
                        stage.setScene(scene);
                        stage.setTitle("Register to Harmonia");
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(navMenuListView.getSelectionModel().getSelectedIndex() == 5){
                    System.out.println("Selected index: 5");

                }
            }
        });
    }
    public void initialize() {
        populateNavbar();
        navActions();
    }
}