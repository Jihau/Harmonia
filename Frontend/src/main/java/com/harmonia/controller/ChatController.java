package com.harmonia.controller;

import java.util.*;

import com.harmonia.model.Message;
import com.harmonia.view.ChatView;

public class ChatController {
    private ChatView view;
    private List<Message> messages;

    public ChatController(ChatView view) {
        this.view = view;
        this.messages = new ArrayList<>();
    }

    public void sendMessage(Message message) {
        // Add the message to the list and update the view
    }
}