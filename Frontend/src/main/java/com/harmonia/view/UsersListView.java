package com.harmonia.view;

import com.harmonia.constants.HarmoniaData;
import com.harmonia.po.UserPO;
import com.harmonia.utils.HarmoniaDataLoader;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;

/**
 * Class representing the list view of the users.
 * author Team Harmonia
 * @version 2.0
 */
public class UsersListView extends ListView {
    public UsersListView() {
        super(HarmoniaData.USERS_LIST);
        this.setOnMouseClicked((EventHandler<Event>) event -> {
            HarmoniaData.SELECTED_RECIPIENT = (UserPO) this.getSelectionModel().getSelectedItem();
            HarmoniaData.MESSAGES_FROM_RECIPIENT = -1;
            HarmoniaDataLoader.loadDirectMessagesByUserId(false);
            // get style for the list view
            this.getStyleClass().add("list-view");
        });
    }
}
