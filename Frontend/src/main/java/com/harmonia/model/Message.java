package com.harmonia.model;
public class Message implements Comparable<Message> {

    private int id;
    private int receiverId;
    private int senderId;
    private String text;
    private String timestamp;

    public Message(int receiver, int sender, int text, String timestamp, String messageText) {
        this.senderId = sender;
        this.receiverId = receiver;
        this.text = messageText;
        this.timestamp = timestamp;
    }

    public String getText() { return  this.text; }

    public int getReceiverID() { return this.receiverId; }

    public int getSenderId() { return this.senderId; }

    public String getTime() { return this.timestamp; }

    public int getMessageId() { return this.id; }

    public void setText(String newText) { this.text = newText; }

    @Override
    public int compareTo(Message other) {
        return this.timestamp.compareTo(other.timestamp);
    }
}