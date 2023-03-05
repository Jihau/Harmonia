package com.harmonia.backend.mappers;

import com.harmonia.backend.domain.DirectMessage;
import com.harmonia.backend.domain.User;
import com.harmonia.backend.po.DMessageRequest;

/**
 * A mapper class for creating DirectMessage objects from DMessageRequest and User objects.
 * @author Harmonia Team
 */
public class DirectMessagesMapper {

    /**
     * Creates a DirectMessage object from a DMessageRequest object, and User objects representing the author and recipient.
     *
     * @param dMessageRequest The DMessageRequest object containing message text and other request data.
     * @param author          The User object representing the author of the message.
     * @param recipient       The User object representing the recipient of the message.
     * @return A DirectMessage object with the message text and author/recipient details loaded from the input parameters.
     */
    public static DirectMessage createDirectMessageFromDirectMessageRequest(DMessageRequest dMessageRequest, User author, User recipient) {
        DirectMessage directMessage = new DirectMessage();
        // load values from request
        directMessage.setMessageText(dMessageRequest.getMessageText());
        // load values dependant for DAO
        directMessage.setAuthor(author);
        directMessage.setRecipient(recipient);
        directMessage.setAuthorId(author.getUserId());
        directMessage.setRecipientId(recipient.getUserId());
        return directMessage;
    }
}
