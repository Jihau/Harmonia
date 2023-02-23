package com.harmonia.backend.service;

import com.harmonia.backend.domain.DirectMessage;
import com.harmonia.backend.domain.Server;
import com.harmonia.backend.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServerService {
    @Autowired
    private ServerRepository serverRepository;

    public ServerService(ServerRepository serverRepository){
        this.serverRepository = serverRepository;
    }

    public Server addServer(Server server){
        if (server.getServerName() == null || server.getServerName().trim().length() == 0){
            return null;
        }
        return serverRepository.save(server);
    }

    public Iterable<Server> listServers(){return serverRepository.findAll();}

    public void deleteServer(Long serverId) {
        serverRepository.deleteById(serverId);
        System.out.println("Server with ID: " + serverId + " is deleted.");
    }

    public void editServerName(Long serverId, String serverName) {
        Optional<Server> server = serverRepository.findById(serverId);
        if (server.isPresent()){
            server.get().setServerName(serverName);
            serverRepository.save(server.get());
            System.out.println("Server id " + serverId + " name has been updated to " + serverName + " successfully");
        } else {
            throw new IllegalArgumentException("Can't change the name");
        }
    }
}