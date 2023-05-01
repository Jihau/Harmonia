package com.harmonia.websockets;

import com.harmonia.utils.HarmoniaDataLoader;
import javafx.application.Platform;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;
import java.util.Map;

public class StompClientSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("New session established. Session Id -> :" + session.getSessionId());
        session.subscribe("/topic/messages", this);
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("Payload:" + payload);
        Platform.runLater(() -> HarmoniaDataLoader.loadDirectMessagesByUserId(true));

    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Map.class;
    }
}
