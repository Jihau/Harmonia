package com.harmonia.backend.service;

import com.harmonia.backend.domain.Server;
import com.harmonia.backend.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
