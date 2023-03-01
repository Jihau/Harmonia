package com.harmonia.backend.service;

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

    public void editServerName(Long serverId, String serverName, String serverCategory) {
        Optional<Server> server = serverRepository.findById(serverId);
        if (server.isPresent()){
            server.get().setServerName(serverName);
            server.get().setServerCategory(serverCategory);
            serverRepository.save(server.get());
            System.out.println("Server id " + serverId + " name has been updated to " + serverName + " successfully");
        } else {
            throw new IllegalArgumentException("Can't change the name");
        }
    }

    public Iterable<Server> listServersByServerName(String serverName){
        return serverRepository.listServersByServerName("%" + serverName +"%");
    }

    public Iterable<Server> listServersByOwnerId(Long userId){
        return serverRepository.listServersByOwnerId(userId);
    }
}
