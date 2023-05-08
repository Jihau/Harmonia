package com.harmonia.backend.mappers;

import com.harmonia.backend.domain.PublicMessage;
import com.harmonia.backend.po.StreamMessageRequest;

/**
 * This class is responsible for mapping StreamMessageRequest to PublicMessage and vice versa.
 * Author: Team Harmonia
 * Version: 2.0
 */
public class StreamMessagesRequestMapper {
    /**
     * This method creates a PublicMessage from a StreamMessageRequest.
     *
     * @param streamMessageRequest The StreamMessageRequest to be mapped.
     * @return The PublicMessage created from the StreamMessageRequest.
     */
    public static PublicMessage createPublicMessageFromStreamMessageRequest(StreamMessageRequest streamMessageRequest) {
        PublicMessage publicMessage = new PublicMessage();
        publicMessage.setMessageText(streamMessageRequest.getText());
        publicMessage.setAuthorId(Long.parseLong(streamMessageRequest.getFrom()));
        publicMessage.setChannelId(Long.parseLong(streamMessageRequest.getTo()));
        return publicMessage;
    }

    /**
     * This method creates a StreamMessageRequest from a PublicMessage.
     *
     * @param publicMessage The PublicMessage to be mapped.
     * @return The StreamMessageRequest created from the PublicMessage.
     */
    public static StreamMessageRequest createStreamMessageRequestFromPublicMessage(PublicMessage publicMessage) {
        StreamMessageRequest streamMessageRequest = new StreamMessageRequest();
        streamMessageRequest.setFrom(publicMessage.getAuthorId().toString());
        streamMessageRequest.setTo(publicMessage.getChannelId().toString());
        streamMessageRequest.setText(publicMessage.getMessageText());
        return streamMessageRequest;
    }
}
