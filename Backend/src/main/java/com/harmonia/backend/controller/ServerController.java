package com.harmonia.backend.controller;

import com.harmonia.backend.domain.Server;
import com.harmonia.backend.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The ServerController class handles HTTP requests related to server management.
 * This class maps incoming requests to the corresponding ServerService methods.
 *
 * @author Harmonia team
 * @version 1.0
 */
@RestController
@RequestMapping("server")
public class ServerController {

    /**
     * The ServerService used to perform server-related operations.
     */
    @Autowired
    private ServerService serverService;

    /**
     * Constructs a new ServerController with the given ServerService.
     *
     * @param serverService the ServerService to use
     */
    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }


    /**
     * Returns a list of servers filtered by serverName.
     *
     * @param serverName the name of the server to filter by
     * @return a list of servers with matching serverName
     */
    @GetMapping("serverName/{serverName}")
    @CrossOrigin
    public Iterable<Server> listServersByServerName(@PathVariable("serverName") String serverName) {
        return serverService.listServersByServerName(serverName);
    }


    /**
     * Returns a list of servers filtered by serverCategory.
     *
     * @param serverCategory the category of the server to filter by
     * @return a list of servers with matching serverCategory
     */
    @GetMapping("serverCategory/{serverCategory}")
    @CrossOrigin
    public Iterable<Server> listServersByServerCategory(@PathVariable("serverCategory") String serverCategory) {
        return serverService.listServersByServerCategory(serverCategory);
    }

    /**
     * Returns a list of servers filtered by ownerId.
     *
     * @param ownerId the ID of the server owner to filter by
     * @return a list of servers with matching ownerId
     */
    @GetMapping("ownerId/{ownerId}")
    @CrossOrigin
    public Iterable<Server> listServersByOwnerId(@PathVariable("ownerId") Long ownerId) {
        return serverService.listServersByOwnerId(ownerId);
    }

    /**
     * Returns a list of all servers.
     *
     * @return a list of all servers
     */
    @GetMapping
    @CrossOrigin
    public Iterable<Server> listServers() {
        return serverService.listServers();
    }

    /**
     * Adds a new server to the system.
     *
     * @param server the server to add
     * @return the newly added server
     */
    @PostMapping
    @CrossOrigin
    public Server addServer(@RequestBody Server server) {
        return serverService.addServer(server);
    }

    /**
     * Deletes a server with the given serverId.
     *
     * @param serverId the ID of the server to delete
     * @return a ResponseEntity indicating the success or failure of the operation
     */
    @DeleteMapping("serverId/{serverId}")
    @CrossOrigin
    public ResponseEntity<String> deleteServer(@PathVariable("serverId") Long serverId) {
        System.out.println("C: Server with id : " + serverId + " is deleted successfully");
        serverService.deleteServer(serverId);
        return new ResponseEntity<>("Server has been deleted", HttpStatus.OK);
    }

    /**
     * Edits the name and category of the server with the given serverId.
     *
     * @param serverId the ID of the server to edit
     * @param server   the new server data to use
     * @return a ResponseEntity indicating the success or failure of the operation
     */
    @PutMapping("serverId/{serverId}")
    @CrossOrigin
    public ResponseEntity<String> editServer(@PathVariable Long serverId, @RequestBody Server server) {
        serverService.editServerName(serverId, server.getServerName(), server.getServerCategory());
        return ResponseEntity.ok("Server name has been edited");
    }

    @GetMapping("serverId/{serverId}")
    @CrossOrigin
    public Server findServerByServerId(@PathVariable("serverId") Long serverId ){
        return serverService.findServerByServerId(serverId);
    }
}
