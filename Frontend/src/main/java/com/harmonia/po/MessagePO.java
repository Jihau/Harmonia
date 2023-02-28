package com.harmonia.po;

import java.io.Serializable;

public class MessagePO implements Serializable, Comparable<MessagePO> {


    int dmessageId;
    int receiverId;
    int senderId;
    String Message_text;
    String timestamp;


    public void setdmessageId(int id) {
        this.dmessageId = id;
    }


    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }


    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }


    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public MessagePO(){

    }

    public MessagePO(int receiver, int sender, int messageId, String timestamp, String messageText) {
        setdmessageId(messageId);
        setReceiverId(receiver);
        setSenderId(sender);
        setText(messageText);
        setTimestamp(timestamp);
    }

    public String getText() { return  this.Message_text; }

    public int getReceiverID() { return this.receiverId; }

    public int getSenderId() { return this.senderId; }

    public String getTime() { return this.timestamp; }

    public int getMessageId() { return this.dmessageId; }

    public void setText(String newText) { this.Message_text = newText; }

    @Override
    public int compareTo(MessagePO other) {
        return this.timestamp.compareTo(other.timestamp);
    }

    public String prettyString() {
        return timestamp + ": " + Message_text;
    }
}