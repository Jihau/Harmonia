package com.harmonia.po;

import java.io.Serializable;

public class MessagePO implements Serializable, Comparable<MessagePO> {


    int dmessageId;
    int recipientId;
    int authorId;
    String messageText;
    String timestamp;

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getDmessageId() {
        return dmessageId;
    }

    public void setDmessageId(int dmessageId) {
        this.dmessageId = dmessageId;
    }

    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(MessagePO other) {
        return this.timestamp.compareTo(other.timestamp);
    }

    @Override
    public String toString() {
        return this.messageText;
    }

    public String prettyString() {
        return timestamp + ": " + messageText;
    }
}