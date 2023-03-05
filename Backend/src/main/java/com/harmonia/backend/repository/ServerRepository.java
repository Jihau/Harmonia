package com.harmonia.backend.repository;

import com.harmonia.backend.domain.Server;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * This interface defines the methods to interact with the "server" table in the database.
 *
 * @author Harmonia team
 * @version 1.0
 */
public interface ServerRepository extends CrudRepository<Server, Long> {

    /**
     * Returns a list of servers that match the given server name.
     *
     * @param serverName The name of the server to search for.
     * @return A list of servers that match the given server name.
     */
    List<Server> listServersByServerName(String serverName);

    /**
     * Returns a list of servers that are owned by the given user.
     *
     * @param userId The ID of the user who owns the servers to search for.
     * @return A list of servers that are owned by the given user.
     */
    List<Server> listServersByOwnerId(Long userId);

    /**
     * Returns an iterable of servers that belong to the given server category.
     *
     * @param serverCategory The category of the servers to search for.
     * @return An iterable of servers that belong to the given server category.
     */
    Iterable<Server> listServersByServerCategory(String serverCategory);

    /**
     * Returns the server with the given ID, or null if no such server exists.
     *
     * @param serverId The ID of the server to search for.
     * @return The server with the given ID, or null if no such server exists.
     */
    Server findByServerId(Long serverId);
}
