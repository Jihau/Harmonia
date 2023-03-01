package com.harmonia.po;

import java.io.Serializable;

public class MessagePO implements Serializable, Comparable<MessagePO> {


    int dmessageId;
    long recipientId;
    long authorId;
    String messageText;
    String timestamp;

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

    public long getauthorId() {
        return authorId;
    }

    public void setauthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
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

    public String prettyString() {
        return timestamp + ": " + messageText;
    }
}