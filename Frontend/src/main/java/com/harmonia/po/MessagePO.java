package com.harmonia.po;
public class MessagePO implements Comparable<MessagePO> {


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

    public MessagePO(){

    }

    public MessagePO(int receiver, int sender, int text, String timestamp, String messageText) {
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
    public int compareTo(MessagePO other) {
        return this.timestamp.compareTo(other.timestamp);
    }

    @Override
    public String toString() {
        return text;
    }
}