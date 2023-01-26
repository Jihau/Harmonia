package com.harmoniaApplication.harmonia.controller;

import java.util.*;

import com.harmoniaApplication.harmonia.model.Message;
import com.harmoniaApplication.harmonia.view.ChatView;

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