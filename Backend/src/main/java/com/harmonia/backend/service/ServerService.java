package com.harmonia.backend.service;

import com.harmonia.backend.domain.Server;
import com.harmonia.backend.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * Service class for managing servers.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Service
public class ServerService {
    @Autowired
    private ServerRepository serverRepository;
    /**
     * Constructor for injecting dependencies.
     *
     * @param serverRepository the server repository to use
     */
    public ServerService(ServerRepository serverRepository){
        this.serverRepository = serverRepository;
    }
    /**
     * Adds a new server to the system.
     *
     * @param server the server to add
     * @return the added server
     * @throws IllegalArgumentException if the server name is null or empty
     */
    public Server addServer(Server server){
        if (server.getServerName() == null || server.getServerName().trim().length() == 0){
            return null;
        }
        return serverRepository.save(server);
    }
    /**
     * Returns a list of all servers in the system.
     *
     * @return a list of servers
     */
    public Iterable<Server> listServers(){return serverRepository.findAll();}
    /**
     * Deletes a server from the system.
     *
     * @param serverId the ID of the server to delete
     */
    public void deleteServer(Long serverId) {
        serverRepository.deleteById(serverId);
        System.out.println("Server with ID: " + serverId + " is deleted.");
    }
    /**
     * Edits the name and category of a server.
     *
     * @param serverId the ID of the server to edit
     * @param serverName the new name for the server
     * @param serverCategory the new category for the server
     * @throws IllegalArgumentException if the server with the given ID does not exist
     */
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
    /**
     * Returns a list of servers with names matching the given search string.
     *
     * @param serverName the search string to match against server names
     * @return a list of matching servers
     */
    public Iterable<Server> listServersByServerName(String serverName){
        return serverRepository.listServersByServerName("%" + serverName +"%");
    }
    /**
     * Returns a list of servers owned by the given user.
     *
     * @param userId the ID of the user who owns the servers
     * @return a list of matching servers
     */
    public Iterable<Server> listServersByOwnerId(Long userId){
        return serverRepository.listServersByOwnerId(userId);
    }
    /**
     * Returns a list of servers with categories matching the given search string.
     *
     * @param serverCategory the search string to match against server categories
     * @return a list of matching servers
     */
    public Iterable<Server> listServersByServerCategory(String serverCategory){
        return serverRepository.listServersByServerCategory("%" + serverCategory +"%");
    }

    public Server findServerByServerId(Long serverId){
        return serverRepository.findByServerId(serverId);
    }

}
