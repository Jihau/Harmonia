package com.harmonia.controller;

import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.constants.HarmoniaViewsConstants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import com.harmonia.po.NavPO;
import com.harmonia.utils.HarmoniaUtils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the navigation bar. Handles user inputs to navigate
 * between different views.
 * @author Harmonia team
 * @version 1.0
 */

public class NavController extends MainViewController {
    @FXML
    private ListView<String> navMenuListView;
    ObservableList<String> navPOObservableList;

    /**
     * Represents the home icon
     */
    Image homeImg = new Image(getClass().getResourceAsStream("/com/harmonia/img/homeIconActive.png"));
    /**
     * Represents the servers icon
     */
    Image serversImg = new Image(getClass().getResourceAsStream("/com/harmonia/img/communitiesIconActive.png"));
    /**
     * Represents the directMessages icon
     */
    Image dmImg = new Image(getClass().getResourceAsStream("/com/harmonia/img/messagesActive.png"));
    /**
     * Represents the profile icon
     */
    Image profileImg = new Image(getClass().getResourceAsStream("/com/harmonia/img/profileIconActive.png"));
    /**
     * Represents the settings icon
     */
    Image settingsImg = new Image(getClass().getResourceAsStream("/com/harmonia/img/settingsIconActive.png"));

    /**
     * Represents the home object on navbar
     */
    NavPO home = new NavPO(0, HarmoniaConstants.textconstants.homeText, homeImg);
    /**
     * Represents the servers object on navbar
     */
    NavPO servers = new NavPO(1, HarmoniaConstants.textconstants.serversText, serversImg);
    /**
     * Represents the directMessages object on navbar
     */
    NavPO dm = new NavPO(2, HarmoniaConstants.textconstants.dmText, dmImg);
    /**
     * Represents the profile object on navbar
     */
    NavPO profile = new NavPO(3, HarmoniaConstants.textconstants.profileText, profileImg);
    /**
     * Represents the settings object on navbar
     */
    NavPO settings = new NavPO(4, HarmoniaConstants.textconstants.settingsText, settingsImg);

    public NavController() throws IOException {
    }

    /**
     * Populates the listview with custom cells using cellFactory based on the variables declared above.
     * Resizes the icons to correct size
     */

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

    /**
     * Handles the event when a user clicks on an option on the navigation bar,
     * if successful, redirects user to the selected view
     */

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

    /**
     * Calls the populateNavbar() method and navActions() method when the view is loaded
     */
    public void initialize() {
        populateNavbar();
        navActions();
    }
}