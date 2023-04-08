package com.harmonia.backend.streams;

import com.harmonia.backend.domain.PublicMessage;
import com.harmonia.backend.mappers.StreamMessagesRequestMapper;
import com.harmonia.backend.po.StreamMessageRequest;
import com.harmonia.backend.service.PublicMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class DirectMessageStream {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private PublicMessageService publicMessageService;

    @MessageMapping("/application")
    @SendTo("/all/messages")
    public StreamMessageRequest send(final StreamMessageRequest message) throws Exception {
        PublicMessage newPublicMessage = publicMessageService.addPublicMessage(StreamMessagesRequestMapper.createPublicMessageFromStreamMessageRequest(message));
        return StreamMessagesRequestMapper.createStreamMessageRequestFromPublicMessage(newPublicMessage);
    }
}