package com.harmonia.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 *
 * This class represents a server entity in the application.
 * It contains information such as serverId, ownerId, serverName, serverCategory, channel, and members.
 * The class is annotated with Lombok annotations: @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor,
 * which generate boilerplate code for getter, setter, no-args constructor, and all-args constructor.
 * The class has two fields of type Set: channel and members, which represent the channels and members belonging to the server.
 * The class overrides the toString() method to return the serverName.
 *
 * @author Harmonia team
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServerPO {
    int serverId;
    int ownerId;
    String serverName;
    String serverCategory;
    Set<ChannelPO> channel;
    Set<ServerMemberPO> members;

    @Override
    public String toString() {
        return serverName;
    }
}
