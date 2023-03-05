package com.harmonia.constants;

import com.harmonia.po.DMessagePO;
import com.harmonia.po.MessagePO;
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
    public static ObservableList<DMessagePO> DIRECT_MESSAGES_LIST = FXCollections.observableArrayList();
    /**
     * The SELECTED_RECIPIENT field stores the currently selected recipient for direct messaging.
     */
    public static UserPO SELECTED_RECIPIENT = null;
    /**
     * The SELECTED_DIRECT_MESSAGE field stores the currently selected direct message.
     */
    public static DMessagePO SELECTED_DIRECT_MESSAGE = null;
    public static int MESSAGES_FROM_RECIPIENT = 0;
}
