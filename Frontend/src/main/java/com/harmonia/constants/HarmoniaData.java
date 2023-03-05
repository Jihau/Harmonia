package com.harmonia.constants;

import com.harmonia.po.UserPO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    public static ObservableList<Object> USERS_LIST = FXCollections.observableArrayList();

    /**
     * The DIRECT_MESSAGES_LIST List contains a list of direct messages for each recipient.
     */
    public static ObservableList<Object> DIRECT_MESSAGES_LIST = FXCollections.observableArrayList();
    public static UserPO SELECTED_RECIPIENT = null;
}
