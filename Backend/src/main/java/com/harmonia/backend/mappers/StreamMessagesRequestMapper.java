package com.harmonia.backend.mappers;

import com.harmonia.backend.domain.PublicMessage;
import com.harmonia.backend.po.StreamMessageRequest;

public class StreamMessagesRequestMapper {
    public static PublicMessage createPublicMessageFromStreamMessageRequest(StreamMessageRequest streamMessageRequest) {
        PublicMessage publicMessage = new PublicMessage();
        publicMessage.setMessageText(streamMessageRequest.getText());
        publicMessage.setAuthorId(Long.parseLong(streamMessageRequest.getFrom()));
        publicMessage.setChannelId(Long.parseLong(streamMessageRequest.getTo()));
        return publicMessage;
    }

    public static StreamMessageRequest createStreamMessageRequestFromPublicMessage(PublicMessage publicMessage) {
        StreamMessageRequest streamMessageRequest = new StreamMessageRequest();
        streamMessageRequest.setFrom(publicMessage.getAuthorId().toString());
        streamMessageRequest.setTo(publicMessage.getChannelId().toString());
        streamMessageRequest.setText(publicMessage.getMessageText());
        return streamMessageRequest;
    }
}
