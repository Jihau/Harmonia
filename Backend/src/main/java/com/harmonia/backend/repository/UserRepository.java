package com.harmonia.backend.repository;

import com.harmonia.backend.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing User entities.
 * Provides basic CRUD operations and some custom methods.
 *
 * @author Harmonia team
 * @version 1.0
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return the user with the specified username, or null if not found
     */
    User findByUsername(String username);

    /**
     * Lists all users whose username contains the specified string.
     *
     * @param username the string to search for in usernames
     * @return a list of users whose username contains the specified string
     */
    List<User> listUsersByUserName(String username);
}
