package com.harmonia.po;

import java.io.Serializable;
import java.util.Set;

public class ServerPO implements Serializable {
    int serverId;
    int ownerId;
    String serverName;
    String serverCategory;
    Set<ChannelPO> channel;
    Set<ServerMemberPO> members;

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerCategory() {
        return serverCategory;
    }

    public void setServerCategory(String serverCategory) {
        this.serverCategory = serverCategory;
    }

    public Set<ChannelPO> getChannel() {
        return channel;
    }

    public void setChannel(Set<ChannelPO> channel) {
        this.channel = channel;
    }

    public Set<ServerMemberPO> getMembers() {
        return members;
    }

    public void setMembers(Set<ServerMemberPO> members) {
        this.members = members;
    }
}
