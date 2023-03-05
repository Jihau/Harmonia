package com.harmonia.backend.mappers;

import com.harmonia.backend.domain.Server;
import com.harmonia.backend.domain.ServerMember;
import com.harmonia.backend.domain.User;
import com.harmonia.backend.po.ServerMemberRequest;

/**
 * A mapper class for creating ServerMember objects from ServerMemberRequest, User, and Server objects.
 */
public class ServerMemberMapper {

    /**
     * Creates a ServerMember object from a ServerMemberRequest object, a User object representing the member, and a Server object.
     *
     * @param serverMemberRequest The ServerMemberRequest object containing data about the member.
     * @param member              The User object representing the member.
     * @param server              The Server object representing the server the member belongs to.
     * @return A ServerMember object with the member's details and server information loaded from the input parameters.
     */
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
