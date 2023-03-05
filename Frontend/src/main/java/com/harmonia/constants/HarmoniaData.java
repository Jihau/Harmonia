package com.harmonia.constants;

import com.harmonia.po.MessagePO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The HarmoniaData class contains constants and data used throughout the Harmonia application.
 * It provides an observable list of users and a map of direct messages, indexed by the recipient's ID.
 * This class is intended to be used as a data holder and should not be instantiated.
 *
 * @author Harmonia Team
 * @version 1.0
 */
public class HarmoniaData {

    /**
     * The USERS_LIST ObservableList contains all users in the system.
     */
    public static ObservableList USERS_LIST = FXCollections.observableArrayList();

    /**
     * The directMessagesByRecipientId Map contains a list of direct messages for each recipient.
     */
    public static Map<Integer, List<MessagePO>> directMessagesByRecipientId = new LinkedHashMap<>();
}
