package com.harmonia.model;
public class Message implements Comparable<Message> {


    int id;

    public void setId(int id) {
        this.id = id;
    }

    int receiverId;

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    int senderId;

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    String text;
   String timestamp;

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

   public Message(){

   }

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

    @Override
    public String toString() {
        return text;
    }
}