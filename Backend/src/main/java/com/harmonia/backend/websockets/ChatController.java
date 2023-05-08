package com.harmonia.backend.websockets;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * This class Represents a controller for the chat.
 * Author: Team Harmonia
 * Version: 2.0
 */
@Controller
public class ChatController {
    /**
     * Sends a message to the chat.
     * @param refreshMessage the message to send.
     * @return the message to send.
     * @throws Exception if the execution fails.
     */
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(final RefreshMessage refreshMessage) throws Exception {

        final String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(refreshMessage.getFrom(), refreshMessage.getText(), time);
    }
}
