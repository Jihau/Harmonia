package com.harmonia.po;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class ChannelPO implements Serializable {
    int channelId;
    int serverId;
    String channelName;
    Date timestamp;
    String channelType;
    Set<PublicMessagePO> publicMessages;

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public Set<PublicMessagePO> getPublicMessages() {
        return publicMessages;
    }

    public void setPublicMessages(Set<PublicMessagePO> publicMessages) {
        this.publicMessages = publicMessages;
    }
}