package com.harmonia.view;

import com.harmonia.constants.HarmoniaData;
import javafx.scene.control.ListView;

public class UsersListView extends ListView {
    public UsersListView() {
        super(HarmoniaData.USERS_LIST);
    }
}
