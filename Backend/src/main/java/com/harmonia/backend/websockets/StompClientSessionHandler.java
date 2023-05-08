package com.harmonia.backend.websockets;

import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * This class Represents a session handler for the web socket.
 * @version 2.0
 * @author Team Harmonia
 */
public class StompClientSessionHandler extends StompSessionHandlerAdapter {

    /**
     * Subscribes to the web socket.
     * @param session the session to subscribe to.
     * @param connectedHeaders the headers to connect to.
     */
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/topic/messages", this);
    }
    /** Handles the frame.
     * @param headers the headers to handle.
     * @param payload the payload to handle.
     */
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("Payload:" + payload);
    }

    /**
     * Gets the payload type.
     * @param headers the headers to get the payload type from.
     * @return the payload type.
     */
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Map.class;
    }
}
