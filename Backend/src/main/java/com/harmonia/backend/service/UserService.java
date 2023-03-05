package com.harmonia.backend.service;

import com.harmonia.backend.domain.User;
import com.harmonia.backend.mappers.UserMapper;
import com.harmonia.backend.po.CreateUserRequest;
import com.harmonia.backend.po.UserResponse;
import com.harmonia.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service class for managing users.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Service
public class UserService {

    /**
     * The repository for user-related database operations
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * The encoder for password hashing and checking
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Constructor for UserService class
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Lists all users.
     *
     * @return iterable of user responses.
     */
    public Iterable<UserResponse> listUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).map(UserResponse::new).collect(Collectors.toList());
    }

    /**
     * Lists users by username.
     *
     * @param userName username to search for.
     * @return iterable of user responses.
     */
    public Iterable<UserResponse> listUsersByUserName(String userName) {
        return userRepository.listUsersByUserName("%" + userName + "%").stream().map(UserResponse::new).collect(Collectors.toList());
    }

    /**
     * Adds a new user.
     *
     * @param user the user to add.
     * @return the added user.
     */
    public User addUser(User user) {
        if (user.getEmail().trim().length() == 0) {
            return null;
        }
        // logic check the username before adding it
        return userRepository.save(user);
    }

    /**
     * Creates a new user.
     *
     * @param user the create user request.
     * @return the created user response.
     * @throws RuntimeException if the username is already taken or if not all fields are filled.
     */
    public UserResponse createUser(CreateUserRequest user) {
        //check if the username is already taken!
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already taken");
        }
        if (user.getUsername().trim().length() != 0 && user.getEmail().trim().length() != 0) {
            //hashing and salting the password
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);

            //Save the user

            return new UserResponse(userRepository.save(UserMapper.createUserFromCreateUserRequest(user)));

        } else {
            throw new RuntimeException("All fields must be filled");
        }

    }

    /**
     * Finds a user by username and password.
     *
     * @param username the username to find.
     * @param password the password to match.
     * @return the user response if found and authenticated, null otherwise.
     */
    public UserResponse findByUsernameAndPassword(String username, String password) {
        //Find the user by username
        User user = userRepository.findByUsername(username);
        System.out.println(user);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("Logged in!");
            return new UserResponse(user);
        } else {
            System.out.println("Failed");
            return null;
        }
    }

    /**
     * Finds a user by username.
     *
     * @param username the username to find.
     * @return the user response if found, null otherwise.
     */
    public UserResponse findByUsername(String username) {
        //Find the user by username
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        System.out.println("response password " + user.getPassword());
        System.out.println(user);
        return new UserResponse(user);
    }

    /**
     * Retrieves a User from the UserRepository by its ID and returns a UserResponse containing the retrieved User.
     *
     * @param userId The ID of the User to retrieve.
     * @return A UserResponse containing the retrieved User.
     * @throws RuntimeException if the User cannot be found in the UserRepository.
     */
    public UserResponse findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return new UserResponse(user.get());
        }
        throw new RuntimeException("User not found");
    }

    /**
     * Deletes a User from the UserRepository by its ID.
     *
     * @param userId The ID of the User to delete.
     */
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        System.out.println("User with id " + userId + " has been deleted!");
    }

    /**
     * Edits a User in the UserRepository with the provided User data and returns a UserResponse containing the edited User.
     *
     * @param userId The ID of the User to edit.
     * @param user   The User object containing the updated User data.
     * @return A UserResponse containing the edited User.
     */
    public UserResponse editUser(Long userId, User user) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            if (user.getProfileIcon() != null) {
                existingUser.get().setProfileIcon(user.getProfileIcon());
            }
            existingUser.get().setBio(user.getBio());
            System.out.println("User's with id " + userId + " has been updated to " + existingUser.get() + " successfully");
            return new UserResponse(userRepository.save(existingUser.get()));
        }
        return null;
    }

    /**
     * Changes the password of a User in the UserRepository and returns a UserResponse containing the updated User.
     *
     * @param userId      The ID of the User to change the password of.
     * @param newPassword The new password for the User.
     * @return A UserResponse containing the updated User with the new password.
     */
    public UserResponse changePassword(Long userId, String newPassword) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            user.setPassword(hashedPassword);
            return new UserResponse(userRepository.save(user));
        }
        return null;
    }
}