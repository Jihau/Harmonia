package com.harmonia.backend.controller;

import com.harmonia.backend.domain.Server;
import com.harmonia.backend.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("server")
public class ServerController {

    @Autowired
    private ServerService serverService;

    public ServerController(ServerService serverService){
        this.serverService = serverService;
    }

    @GetMapping
    @CrossOrigin
    public Iterable<Server> listServers(){
        return serverService.listServers();
    }

    @PostMapping
    @CrossOrigin
    public Server addServer(@RequestBody Server server){
        return serverService.addServer(server);
    }
}
