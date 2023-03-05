package com.harmonia.constants;

public class HarmoniaMessagesConstants {
    /**
     * Title for the login view
     */
    public static final String WINDOW_TITLE_SIGN_IN_MESSAGE = "Sign in to Harmonia";
    /**
     * Title for the main view
     */
    public static final String WINDOW_TITLE_HOME_MESSAGE = "Welcome to Harmonia";
    /**
     * Title for the profile view
     */
    public static final String WINDOW_TITLE_MY_PROFILE_MESSAGE = "Your Profile";
    /**
     * Title for the your communities view
     */
    public static final String WINDOW_TITLE_MY_SERVERS_MESSAGE = "Your communities";
    /**
     * Title for the settings view
     */
    public static final String WINDOW_TITLE_MY_SETTINGS_MESSAGE = "Your Settings";
    /**
     * Title for the direct message view
     */
    public static final String WINDOW_TITLE_DIRECT_MESSAGES_MESSAGE = "Your Direct Messages";

    /**
     * String that is added in front of message if the logged in user is the sender
     */
    public static final String LABEL_DIRECT_MESSAGES_AUTHOR = "you: ";
    /**
     * Empty String for comparison
     */
    public static final String LABEL_EMPTY_STRING = "";
    /**
     * Error message shown when sending a message failed
     */
    public static final String ERROR_SENDING_MESSAGE = "There was an error sending the message";
    /**
     * Error message shown when the user tries to send an empty message
     */
    public static final String ERROR_EMPTY_MESSAGE = "Please fill me before submitting!";

    // Error Messages
    /**
     * Error title shown when the user tries to edit or remove a direct message they did not send.
     */
    public static final String DIRECT_MESSAGES_NOT_YOURS_TITLE = "Not your message";
    /**
     * Error message shown when removing a direct message failed. 
     */
    public static final String DIRECT_MESSAGES_NOT_YOURS_HEADER_TEXT = "Can't delete message";
    /**
     * Error message text body shown when the user tries to delete a direct message they didn't send. 
     */
    public static final String DIRECT_MESSAGES_NOT_YOURS_BODY_TEXT = "You can only delete messages that you've sent!";
    /**
     * Error message title shown when removing a message failed.
     */
    public static final String DIRECT_MESSAGES_CANNOT_DELETE_TITLE = "Error deleting message";
    /**
     * Error message header shown when removing a direct message failed.
     */
    public static final String DIRECT_MESSAGES_CANNOT_DELETE_HEADER_TEXT = "Can't delete message";
    /**
     * Error message body shown when removing a direct message failed.
     */
    public static final String DIRECT_MESSAGES_CANNOT_DELETE_BODY_TEXT = "Something went wrong";
    /**
     * Error message title shown when the user tries to remove a message without selecting a message first.
     */
    public static final String DIRECT_MESSAGES_ERROR_NOT_SELECTED_TITLE = "Error";
    /**
     * Error message header shown when the user tries to remove a message without selecting a message first.
     */
    public static final String DIRECT_MESSAGES_ERROR_NOT_SELECTED_HEADER = "No message selected";
    /**
     * Error message body shown when the user tries to remove a message without selecting a message first.
     */
    public static final String DIRECT_MESSAGES_ERROR_NOT_SELECTED_BODY = "Please select a message";

    /**
     * Message text shown when confirming if a user wants to remove a direct message.
     */ 
    public static final String DIRECT_MESSAGES_DELETE_CONFIRMATION_MESSAGE = "Delete message?";
    /**
     * Message title shown when confirming if a user wants to remove a direct message.
     */
    public static final String DIRECT_MESSAGES_DELETE_CONFIRMATION_TITLE = "Delete message";
    /**
     * Message header shown when confirming if a user wants to remove a direct message.
     */
    public static final String DIRECT_MESSAGES_DELETE_CONFIRMATION_HEADER = "Are you sure you want to delete this message?";
    /**
     * Message body shown when confirming if a user wants to remove a direct message.
     */
    public static final String DIRECT_MESSAGES_DELETE_CONFIRMATION_BODY = "Deleting a message is an irreversible action and cannot be undone.";
}
