package com.harmonia.view;

import com.harmonia.constants.HarmoniaData;
import com.harmonia.po.DMessagePO;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;

/**
 * Class representing the list view of the messages.
 * @author Team Harmonia
 * @version 2.0
 */
public class MessagesListView extends ListView<DMessagePO> {
    public MessagesListView() {
        super(HarmoniaData.DIRECT_MESSAGES_LIST);
        this.setOnMouseClicked((EventHandler<Event>) event -> {
            HarmoniaData.SELECTED_DIRECT_MESSAGE = this.getSelectionModel().getSelectedItem();
        });
    }
}
