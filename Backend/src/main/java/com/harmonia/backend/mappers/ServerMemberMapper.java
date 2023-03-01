package com.harmonia.backend.mappers;

import com.harmonia.backend.domain.Server;
import com.harmonia.backend.domain.ServerMember;
import com.harmonia.backend.domain.User;
import com.harmonia.backend.po.ServerMemberRequest;

public class ServerMemberMapper {
    public static ServerMember createServerMemberFromServerMemberRequest(ServerMemberRequest serverMemberRequest, User member, Server server) {
        ServerMember serverMember = new ServerMember();
        serverMember.setMember(member);
        serverMember.setMemberId(member.getUserId());
        serverMember.setServer(server);
        serverMember.setServerId(server.getServerId());
        serverMember.setNickName(serverMemberRequest.getNickName());
        return serverMember;
    }
}
