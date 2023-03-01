package com.harmonia.po;

import java.io.Serializable;
import java.util.*;

public class PublicMessagePO implements Serializable {
    String messageText;
    Long pmessageId;

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Long getPmessageId() {
        return pmessageId;
    }

    public void setPmessageId(Long pmessageId) {
        this.pmessageId = pmessageId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    Long authorId;
    Long channelId;
    Date timestamp;
}
