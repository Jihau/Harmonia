package com.harmonia.utils;

import com.harmonia.client.DirectMessageClient;
import com.harmonia.client.UserClient;
import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.constants.HarmoniaData;
import com.harmonia.po.MessagePO;
import com.harmonia.po.UserPO;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;


/**
 * This class contains methods to load user data, load direct messages between users, and interact with direct messages.
 *
 * @author Harmonia team
 * @version 1.0
 */
public class HarmoniaDataLoader {

    /**
     * A client for interacting with the user service.
     */
    private static final UserClient userClient = new UserClient();

    /**
     * A client for interacting with the direct message service.
     */
    private static final DirectMessageClient directMessageClient = new DirectMessageClient();

    /**
     * Loads users whose username contains the specified text and adds them to the USERS_LIST.
     *
     * @param userName the text to search for in usernames
     */
    public static void searchUserByUsername(String userName) {
        UserPO[] users = userClient.listUsers();
        HarmoniaData.USERS_LIST.clear();
        Collections.addAll(HarmoniaData.USERS_LIST, users);
    }

    /**
     * Loads messages of a conversation between the logged-in User and the recipient.
     */
    public static void loadDirectMessagesByUserId() {
        int authorId = HarmoniaConstants.LOGGED_USERS.getUserId();
        int recipientId = HarmoniaData.SELECTED_RECIPIENT.getUserId();
        Comparator<MessagePO> comparator = Comparator.comparingInt(MessagePO::getDmessageId);
        HarmoniaData.DIRECT_MESSAGES_LIST.clear();
        for (MessagePO m : Objects.requireNonNull(directMessageClient.getMessagesByRecipientID(recipientId).getBody())) {
            if (m.getAuthorId() == recipientId) HarmoniaData.DIRECT_MESSAGES_LIST.add(m);
        }
        for (MessagePO m : Objects.requireNonNull(directMessageClient.getMessagesByAuthorId(authorId).getBody())) {
            if (m.getRecipientId() == recipientId) {
                HarmoniaData.DIRECT_MESSAGES_LIST.add(m);
            }
        }
        HarmoniaData.DIRECT_MESSAGES_LIST.sort(comparator);
        HarmoniaData.SELECTED_DIRECT_MESSAGE = null;
    }

    /**
     * Sends a message to the recipient specified in the message object and returns the response from the message client.
     *
     * @param messageText the message to be sent
     * @return true if the message was successfully sent, false otherwise
     */
    public static boolean sendDirectMessage(String messageText) {
        MessagePO newMessage = new MessagePO();
        newMessage.setMessageText(messageText);
        newMessage.setAuthorId(HarmoniaConstants.LOGGED_USERS.getUserId());
        newMessage.setRecipientId(HarmoniaData.SELECTED_RECIPIENT.getUserId());
        return directMessageClient.addMessage(newMessage).getStatusCode().is2xxSuccessful();
    }

    /**
     * Edits the currently selected direct message with the specified text.
     *
     * @param messageText the new text of the message
     * @return true if the message was successfully edited, false otherwise
     */
    public static boolean editDirectMessage(String messageText) {
        if (messageText == null || messageText.isBlank()) {
            return false;
        }
        HarmoniaData.SELECTED_DIRECT_MESSAGE.setMessageText(messageText);
        return directMessageClient.editMessage(HarmoniaData.SELECTED_DIRECT_MESSAGE).getStatusCode().is2xxSuccessful();
    }

    /**
     * Deletes the currently selected direct message.
     *
     * @return true if the message was successfully deleted, false otherwise
     */
    public static boolean deleteDirectMessage() {
        if (HarmoniaData.SELECTED_DIRECT_MESSAGE == null) {
            return false;
        }
        if (directMessageClient.removeMessage(HarmoniaData.SELECTED_DIRECT_MESSAGE).getStatusCode().is2xxSuccessful()) {
            HarmoniaData.SELECTED_DIRECT_MESSAGE = null;
            HarmoniaDataLoader.loadDirectMessagesByUserId();
            return true;
        }
        return false;
    }
}
