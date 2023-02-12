package com.harmonia.view;
import com.harmonia.model.Message;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChatView {
    private Stage stage;
    private Scene scene;
    private BorderPane root;
    private TextArea chatArea;
    private TextField messageField;

    public ChatView() {
        // Initialize the GUI elements
    }

    public void show() {
        // Show the chat window
    }

    public void addMessage(Message message) {
        // Add the message to the chat area
    }
}