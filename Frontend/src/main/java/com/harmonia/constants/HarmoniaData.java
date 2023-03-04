package com.harmonia.constants;

import com.harmonia.po.MessagePO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HarmoniaData {

    public static ObservableList USERS_LIST = FXCollections.observableArrayList();
    public static Map<Integer, List<MessagePO>> directMessagesByRecipientId = new LinkedHashMap<>();
}
