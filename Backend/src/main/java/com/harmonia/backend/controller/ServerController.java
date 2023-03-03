package com.harmonia.backend.controller;

import com.harmonia.backend.domain.Server;
import com.harmonia.backend.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("server")
public class ServerController {

    @Autowired
    private ServerService serverService;

    public ServerController(ServerService serverService){
        this.serverService = serverService;
    }


    @GetMapping("serverName/{serverName}")
    @CrossOrigin
    public Iterable<Server> listServersByServerName(@PathVariable("serverName") String serverName){
        return serverService.listServersByServerName(serverName);
    }

    @GetMapping("category/{serverCategory}")
    @CrossOrigin
    public Iterable<Server> listServersByServerCategory(@PathVariable("serverCategory") String serverCategory){
        return serverService.listServersByServerCategory(serverCategory);
    }

    @GetMapping("ownerId/{ownerId}")
    @CrossOrigin
    public Iterable<Server> listServersByOwnerId(@PathVariable("ownerId") Long ownerId){
        return serverService.listServersByOwnerId(ownerId);
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

    @DeleteMapping
    @RequestMapping("serverId/{serverId}")
    @CrossOrigin
    public ResponseEntity<String> deleteServer(@PathVariable("serverId") Long serverId) {
        System.out.println("C: Server with id : " + serverId + " is deleted successfully");
        serverService.deleteServer(serverId);
        return new ResponseEntity<>("Server has been deleted", HttpStatus.OK);
    }

    @PutMapping("serverId/{serverId}")
    @CrossOrigin
    public ResponseEntity<String> editServer(@PathVariable Long serverId, @RequestBody Server server){
        serverService.editServerName(serverId, server.getServerName(), server.getServerCategory());
        return ResponseEntity.ok("Server name has been edited");
    }
}
