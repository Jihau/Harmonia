package com.harmonia.controller;

import com.harmonia.HarmoniaApplication;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.constants.HarmoniaViewsConstants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import com.harmonia.po.NavPO;
import com.harmonia.utils.HarmoniaUtils;

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

    NavPO home = new NavPO(0, HarmoniaConstants.textconstants.homeText, homeImg);
    NavPO servers = new NavPO(1, HarmoniaConstants.textconstants.serversText, serversImg);
    NavPO dm = new NavPO(2, HarmoniaConstants.textconstants.dmText, dmImg);
    NavPO profile = new NavPO(3, HarmoniaConstants.textconstants.profileText, profileImg);
    NavPO settings = new NavPO(4, HarmoniaConstants.textconstants.settingsText, settingsImg);

    public NavController() throws IOException {
    }

    public void populateNavbar() {
        navPOObservableList = FXCollections.observableArrayList();
        navPOObservableList.addAll(
                home.getNavName(), servers.getNavName(), dm.getNavName(), profile.getNavName(), settings.getNavName());
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
                    } else if (name.equals(servers.getNavName())) {
                        imageView.setFitHeight(23.0);
                        imageView.setFitWidth(23.0);
                        imageView.setImage(servers.getImage());
                    } else if (name.equals(dm.getNavName())) {
                        imageView.setFitHeight(23.0);
                        imageView.setFitWidth(23.0);
                        imageView.setImage(dm.getImage());
                    } else if (name.equals(profile.getNavName())) {
                        imageView.setFitHeight(23.0);
                        imageView.setFitWidth(23.0);
                        imageView.setImage(profile.getImage());
                    } else if (name.equals(settings.getNavName())) {
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

    public void navActions() {
        navMenuListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (navMenuListView.getSelectionModel().getSelectedIndex() == 0) {
                    System.out.println("Home clicked");
                    try {
                        HarmoniaUtils.loadJavaFxView(
                                HarmoniaConstants.textconstants.homeText, HarmoniaViewsConstants.HOME_VIEW,
                                (Stage) navMenuListView.getScene().getWindow());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if (navMenuListView.getSelectionModel().getSelectedIndex() == 1) {
                    System.out.println("Servers clicked");
                    try {
                        HarmoniaUtils.loadJavaFxView(
                                HarmoniaConstants.textconstants.myServersTitle,
                                HarmoniaViewsConstants.USER_SERVERS_VIEW,
                                (Stage) navMenuListView.getScene().getWindow());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if (navMenuListView.getSelectionModel().getSelectedIndex() == 2) {
                    System.out.println("DMs clicked");
                    try {
                        HarmoniaUtils.loadJavaFxView(
                                HarmoniaConstants.textconstants.dmText,
                                HarmoniaViewsConstants.DIRECT_MESSAGES_VIEW,
                                (Stage) navMenuListView.getScene().getWindow());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if (navMenuListView.getSelectionModel().getSelectedIndex() == 3) {
                    System.out.println("Profile clicked");
                    try {
                        HarmoniaUtils.loadJavaFxView(
                                HarmoniaConstants.textconstants.profileText,
                                HarmoniaViewsConstants.PROFILE_VIEW,
                                (Stage) navMenuListView.getScene().getWindow());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (navMenuListView.getSelectionModel().getSelectedIndex() == 4) {
                    System.out.println("Settings clicked");
                    try {
                        HarmoniaUtils.loadJavaFxView(
                                HarmoniaConstants.textconstants.settingsText,
                                HarmoniaViewsConstants.USER_SETTINGS_VIEW,
                                (Stage) navMenuListView.getScene().getWindow());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if (navMenuListView.getSelectionModel().getSelectedIndex() == 5) {
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