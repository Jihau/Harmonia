package com.harmonia.view;

import com.harmonia.constants.HarmoniaData;
import com.harmonia.po.UserPO;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;

public class UsersListView extends ListView {
    public UsersListView() {
        super(HarmoniaData.USERS_LIST);
        this.setOnMouseClicked((EventHandler<Event>) event -> {
            HarmoniaData.SELECTED_RECIPIENT = (UserPO) this.getSelectionModel().getSelectedItem();
        });
    }
}
