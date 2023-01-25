package com.example.harmonia.model;
public class Message {
    private String sender;
    private String text;
    private String timestamp;

    public Message(String sender, String text, String timestamp) {
        this.sender = sender;
        this.text = text;
        this.timestamp = timestamp;
    }
    //here goes the getters and setters
}