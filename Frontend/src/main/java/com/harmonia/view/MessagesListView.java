package com.harmonia.view;

import com.harmonia.constants.HarmoniaData;
import com.harmonia.po.UserPO;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;

public class MessagesListView extends ListView {
    public MessagesListView() {
        super(HarmoniaData.DIRECT_MESSAGES_LIST);
        this.setOnMouseClicked((EventHandler<Event>) event -> {
            HarmoniaData.SELECTED_RECIPIENT = (UserPO) this.getSelectionModel().getSelectedItem();
        });
    }
}
