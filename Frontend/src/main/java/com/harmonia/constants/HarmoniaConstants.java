package com.harmonia.constants;

import com.harmonia.po.UserPO;
import com.harmonia.utils.HarmoniaUtils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class contains all the constants used in the Harmonia application.
 *
 * @author Harmonia Team
 * @version 1.0
 */
public class HarmoniaConstants {

    /**
     * The base URL of the Harmonia server.
     */
    public static final String BASE_URL = "http://13.51.150.175";
    //public static final String BASE_URL = "http://localhost:80";

    public static final String SOCKETS_URL = "ws://13.51.150.175/chat";
    //public static final String SOCKETS_URL = "ws://localhost/chat";

    /**
     * The URL for deleting a user.
     */
    public static final String USERS_DELETE_URL = BASE_URL + "/user/userId/{userId}";
    /**
     * The URL for getting a list of users.
     */
    public static final String USERS_LIST_URL = BASE_URL + "/user";
    /**
     * The URL for adding a new user.
     */
    public static final String USERS_ADD_URL = BASE_URL + "/user";
    /**
     * The URL for editing an existing user's details.
     */
    public static final String USERS_EDIT_URL = BASE_URL + "/user/userId/{userId}";
    /**
     * The URL for logging in a user.
     */
    public static final String USERS_LOGIN_URL = BASE_URL + "/user/login";
    /**
     * The URL for adding a new direct message.
     */
    public static final String DM_ADD_URL = BASE_URL + "/dmessage";
    /**
     * The URL for getting all direct messages.
     */
    public static final String DM_GETALL_URL = BASE_URL + "/dmessage";
    /**
     * The URL for editing an existing direct message.
     */
    public static final String DM_EDIT_URL = BASE_URL + "/dmessage/messageId/{DmessageId}";
    /**
     * The URL for getting all direct messages sent by a specific author.
     */
    public static final String DM_GET_A_ID = BASE_URL + "/dmessage/authorId/{authorId}";
    /**
     * The URL for getting all direct messages between two users.
     */
    public static final String DM_GET_CONVERSATION = BASE_URL
            + "/dmessage/authorId/{authorId}/recipientId/{recipientId}";
    /**
     * The URL for getting all direct messages sent to a specific recipient.
     */
    public static final String DM_GET_R_ID = BASE_URL + "/dmessage/recipientId/{recipientId}";
    /**
     * The URL for getting a list of all channels.
     */
    public static final String CHANNEL_LIST_ALL_URL = BASE_URL + "/channel";
    /**
     * The URL for adding a new channel.
     */
    public static final String CHANNEL_ADD_URL = BASE_URL + "/channel";
    /**
     * The URL for deleting an existing channel.
     */
    public static final String CHANNEL_DELETE_URL = BASE_URL + "/channel/{channelId}";
    /**
     * The URL for editing an existing channel's details.
     */
    public static final String CHANNEL_EDIT_URL = BASE_URL + "/channel/{channelId}";
    /**
     * The URL for getting a list of all servers.
     */
    public static final String SERVER_LIST_ALL_URL = BASE_URL + "/server";
    /**
     * The URL for getting a list of servers by name.
     */
    public static final String SERVER_LIST_BY_NAME_URL = BASE_URL + "/server/name/{serverName}";
    /**
     * The URL for getting a list of servers by server category.
     */
    public static final String SERVER_LIST_BY_CATEGORY_URL = BASE_URL + "/server/serverCategory/{serverCategory}";
    /**
     * The URL for getting a list of servers by member ID.
     */
    public static final String SERVER_LIST_BY_MEMBER_ID_URL = BASE_URL + "/member/memberId/{memberId}";
    /**
     * The URL for creating a new server
     */
    public static final String SERVER_ADD_NEW_SERVER_URL = BASE_URL + "/server";
    /**
     * URL for a user joining a server
     */
    public static final String ADD_MEMBER_TO_SERVER_URL = BASE_URL + "/member";
    /**
     * The URL for getting a server by its ID.
     */
    public static final String SERVER_LIST_BY_ID_URL = BASE_URL + "/server/serverId/{serverId}";
    /**
     * The URL for getting a list of members by server ID.
     */
    public static final String MEMBERS_BY_SERVER_ID_URL = BASE_URL + "/member/serverId/{serverId}";
    /**
     * The URL for getting all public messages.
     */
    public static final String PM_GETALL_URL = BASE_URL + "/pmessage";
    /**
     * The URL for adding a new public message.
     */
    public static final String PM_ADD_URL = BASE_URL + "/pmessage";
    /**
     * The URL for removing a public message by ID.
     */
    public static final String PM_REMOVE_URL = BASE_URL + "/pmessage/messageId/{pMessageId}";
    /**
     * The URL for editing a public message by ID.
     */
    public static final String PM_EDIT_URL = BASE_URL + "/pmessage/messageId/{pMessageId}";
    /**
     * The URL for adding a friend.
     */
    public static final String FRIEND_ADD_URL = BASE_URL + "/friends/userId/{userId}";
    /**
     * The URL for getting a friend list by user ID.
     */
    public static final String FRIEND_LIST_BY_USERID_URL = BASE_URL + "/friends/userId/{userId}";
    /**
     * The URL for removing a friend by ID.
     */
    public static final String FRIEND_REMOVE_URL = BASE_URL + "/friends/remove/userId/{userId}";
    /**
     * Header value of key.
     */
    public static final String API_KEY_HEADER_NAME = "Harmonia-api-key";
    public static ResourceBundle texts = null;
    public static Locale selectedLocale = null;
    public static HarmoniaMessagesConstants messages;
    public static TextConstants textconstants;
    /**
     * The currently logged-in user.
     */
    public static UserPO LOGGED_USERS = null;
    /**
     * Keyword to generate key.
     */
    public static String KEYWORD_GENERATE_KEY = "ha6BrHz1/LYT5f8NX+WM5pmMHH8PLzF+fC12vavd/XE=";

    /**
     * Sets the application language to the given locale.
     * @param locale
     */
    public static void setLanguage(Locale locale) {

        String langVariant = locale.getVariant();
        if (langVariant.equalsIgnoreCase("fi") || langVariant.equalsIgnoreCase("ar") || langVariant.equalsIgnoreCase("es") || langVariant.equalsIgnoreCase("fr")
        || langVariant.equalsIgnoreCase("zh") || langVariant.equalsIgnoreCase("de") || langVariant.equalsIgnoreCase("Fa")
        || langVariant.equalsIgnoreCase("ja") || langVariant.equalsIgnoreCase("it") || langVariant.equalsIgnoreCase("hi")
        || langVariant.equalsIgnoreCase("ru") || langVariant.equalsIgnoreCase("sv")) {
            langVariant = langVariant.toLowerCase();
            selectedLocale = locale;
        } else {
            langVariant = "En";
            selectedLocale = new Locale("English", "United Kingdom", "En");
        }
        texts = ResourceBundle.getBundle("properties/language" + langVariant);
        setMessages();
        HarmoniaUtils.loadContentfulLabels();
    }

    /**
     * loads messages for labels and other elements.
     */
    public static void setMessages() {
        HarmoniaConstants.textconstants = new TextConstants();
        HarmoniaConstants.messages = new HarmoniaMessagesConstants();
    }
}
