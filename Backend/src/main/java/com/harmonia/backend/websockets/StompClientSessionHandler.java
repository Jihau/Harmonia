package com.harmonia.backend.websockets;

import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;
import java.util.Map;

public class StompClientSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/topic/messages", this);
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("Payload:" + payload);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Map.class;
    }
}
