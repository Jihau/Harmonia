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

@Service
public class ServerMemberService {
    @Autowired
    ServerMemberRepository serverMemberRepository;

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ServerMember> listServersByMemberId(Long memberId) {
        return serverMemberRepository.listServersByMemberId(memberId);
    }

    public List<ServerMember> listServerMembersByServerId(Long serverId){
        return serverMemberRepository.listServerMembersByServerId(serverId);
    }

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
