package com.harmonia.view;

import com.harmonia.constants.HarmoniaData;
import com.harmonia.po.MessagePO;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;

public class MessagesListView extends ListView {
    public MessagesListView() {
        super(HarmoniaData.DIRECT_MESSAGES_LIST);
        this.setOnMouseClicked((EventHandler<Event>) event -> {
            HarmoniaData.SELECTED_DIRECT_MESSAGE = (MessagePO) this.getSelectionModel().getSelectedItem();
        });
    }
}
