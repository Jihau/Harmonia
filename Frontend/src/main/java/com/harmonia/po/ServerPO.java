package com.harmonia.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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
}
