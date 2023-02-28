package com.harmonia.backend.mappers;

import com.harmonia.backend.domain.DirectMessage;
import com.harmonia.backend.domain.User;
import com.harmonia.backend.po.DMessageRequest;

public class DirectMessagesMapper {
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
