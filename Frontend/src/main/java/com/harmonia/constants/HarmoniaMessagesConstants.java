package com.harmonia.constants;

public class HarmoniaMessagesConstants {


    /**
     * Title for the login view
     */
    public String WINDOW_TITLE_SIGN_IN_MESSAGE =  HarmoniaConstants.texts.getString("signInTitleText");
    /**
     * Title for the main view
     */
    public String WINDOW_TITLE_HOME_MESSAGE =  HarmoniaConstants.texts.getString("homeTitleText");
    /**
     * Title for the profile view
     */
    public String WINDOW_TITLE_MY_PROFILE_MESSAGE =  HarmoniaConstants.texts.getString("profileTitleText");
    /**
     * Title for the your communities view
     */
    public String WINDOW_TITLE_MY_SERVERS_MESSAGE =  HarmoniaConstants.texts.getString("myServersTitle");
    /**
     * Title for the settings view
     */
    public String WINDOW_TITLE_MY_SETTINGS_MESSAGE =  HarmoniaConstants.texts.getString("yourSettingsTitle");
    /**
     * Title for the direct message view
     */
    public String WINDOW_TITLE_DIRECT_MESSAGES_MESSAGE =  HarmoniaConstants.texts.getString("dmTitleText");

    /**
     * String that is added in front of message if the logged in user is the sender
     */
    public String LABEL_DIRECT_MESSAGES_AUTHOR =  HarmoniaConstants.texts.getString("messagesAuthorText");
    /**
     * Empty String for comparison
     */
    public String LABEL_EMPTY_STRING = "";
    /**
     * Error message shown when sending a message failed
     */
    public String ERROR_SENDING_MESSAGE =  HarmoniaConstants.texts.getString("errorSendingMessage");
    // Error Messages
    /**
     * Error message shown when the user tries to send an empty message
     */
    public String ERROR_EMPTY_MESSAGE =  HarmoniaConstants.texts.getString("errorEmptyMessage");
    /**
     * Error title shown when the user tries to edit or remove a direct message they did not send.
     */
    public String DIRECT_MESSAGES_NOT_YOURS_TITLE =  HarmoniaConstants.texts.getString("dmNotYoursTitleText");
    /**
     * Error message shown when removing a direct message failed. 
     */
    public String DIRECT_MESSAGES_NOT_YOURS_HEADER_TEXT =  HarmoniaConstants.texts.getString("dmNotYoursHeaderText");
    /**
     * Error message text body shown when the user tries to delete a direct message they didn't send. 
     */
    public String DIRECT_MESSAGES_NOT_YOURS_BODY_TEXT =  HarmoniaConstants.texts.getString("dmNotYoursBodyText");
    /**
     * Error message title shown when removing a message failed.
     */
    public String DIRECT_MESSAGES_CANNOT_DELETE_TITLE =  HarmoniaConstants.texts.getString("dmCannotDeleteTitleText");
    /**
     * Error message header shown when removing a direct message failed.
     */
    public String DIRECT_MESSAGES_CANNOT_DELETE_HEADER_TEXT =  HarmoniaConstants.texts.getString("dmCannotDeleteHeaderText");
    /**
     * Error message body shown when removing a direct message failed.
     */
    public String DIRECT_MESSAGES_CANNOT_DELETE_BODY_TEXT =  HarmoniaConstants.texts.getString("dmCannotDeleteBodyText");
    /**
     * Error message title shown when the user tries to remove a message without selecting a message first.
     */
    public String DIRECT_MESSAGES_ERROR_NOT_SELECTED_TITLE =  HarmoniaConstants.texts.getString("dmErrorNotSelectedTitleText");
    /**
     * Error message header shown when the user tries to remove a message without selecting a message first.
     */
    public String DIRECT_MESSAGES_ERROR_NOT_SELECTED_HEADER =  HarmoniaConstants.texts.getString("dmErrorNotSelectedHeaderText");
    /**
     * Error message body shown when the user tries to remove a message without selecting a message first.
     */
    public String DIRECT_MESSAGES_ERROR_NOT_SELECTED_BODY =  HarmoniaConstants.texts.getString("dmErrorNotSelectedBodyText");

    /**
     * Message text shown when confirming if a user wants to remove a direct message.
     */ 
    public String DIRECT_MESSAGES_DELETE_CONFIRMATION_MESSAGE =  HarmoniaConstants.texts.getString("dmDeleteConfirmationMessageText");
    /**
     * Message title shown when confirming if a user wants to remove a direct message.
     */
    public String DIRECT_MESSAGES_DELETE_CONFIRMATION_TITLE =  HarmoniaConstants.texts.getString("dmDeleteConfirmationTitleText");
    /**
     * Message header shown when confirming if a user wants to remove a direct message.
     */
    public String DIRECT_MESSAGES_DELETE_CONFIRMATION_HEADER =  HarmoniaConstants.texts.getString("dmDeleteConfirmationHeaderText");
    /**
     * Message body shown when confirming if a user wants to remove a direct message.
     */
    public String DIRECT_MESSAGES_DELETE_CONFIRMATION_BODY =  HarmoniaConstants.texts.getString("dmDeleteConfirmationBodyText");
}
