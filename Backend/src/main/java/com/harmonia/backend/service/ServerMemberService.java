package com.harmonia.backend.service;

import com.harmonia.backend.domain.Server;
import com.harmonia.backend.domain.ServerMember;
import com.harmonia.backend.domain.User;
import com.harmonia.backend.mappers.ServerMemberMapper;
import com.harmonia.backend.po.ServerMemberRequest;
import com.harmonia.backend.repository.ServerMemberRepository;
import com.harmonia.backend.repository.ServerRepository;
import com.harmonia.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The ServerMemberService class provides methods for adding, listing, and editing members in the servers.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Service
public class ServerMemberService {

    /**
     * This annotation is used to automatically inject an instance of ServerMemberRepository
     * when this service is created, allowing us to use its methods to interact with the database.
     */
    @Autowired
    ServerMemberRepository serverMemberRepository;

    /**
     * This annotation is used to automatically inject an instance of ServerRepository
     * when this service is created, allowing us to use its methods to interact with the database.
     */
    @Autowired
    private ServerRepository serverRepository;

    /**
     * This annotation is used to automatically inject an instance of UserRepository
     * when this service is created, allowing us to use its methods to interact with the database.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Returns a list of servers that a member is a part of.
     *
     * @param memberId the ID of the member to search for.
     * @return a list of ServerMember objects representing the servers that the member belongs to.
     */
    public List<ServerMember> listServersByMemberId(Long memberId) {
        return serverMemberRepository.listServersByMemberId(memberId);
    }

    /**
     * Returns a list of members belonging to a server.
     *
     * @param serverId the ID of the server to search for.
     * @return a list of ServerMember objects representing the members belonging to the server.
     */
    public List<ServerMember> listServerMembersByServerId(Long serverId) {
        return serverMemberRepository.listServerMembersByServerId(serverId);
    }

    /**
     * Adds a member to a server.
     *
     * @param serverMemberRequest a ServerMemberRequest object containing the IDs of the member and server to add to.
     * @return a ServerMember object representing the added member and server association.
     * @throws Exception if the member or server cannot be found.
     */
    public ServerMember addMemberToServer(ServerMemberRequest serverMemberRequest) throws Exception {
        Optional<User> member = userRepository.findById(serverMemberRequest.getMemberId());
        if (member.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        Optional<Server> server = serverRepository.findById(serverMemberRequest.getServerId());
        if (server.isEmpty()) {
            throw new RuntimeException("Server not found");
        }
        return serverMemberRepository.save(ServerMemberMapper.createServerMemberFromServerMemberRequest(serverMemberRequest, member.get(), server.get()));
    }
}
