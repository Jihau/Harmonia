package com.harmonia.client;

import com.harmonia.po.UserPO;
import com.harmonia.utils.HarmoniaUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.harmonia.constants.HarmoniaConstants.*;

/**
 * This class provides a client implementation to interact with the Harmonia user service API.
 *
 * @author Harmonia Team
 * @version 1.0
 */
public class UserClient {
    protected RestTemplate restTemplate;

    /**
     * Constructor for UserClient.
     * Creates a new RestTemplate and adds a MappingJackson2HttpMessageConverter to it.
     */
    public UserClient() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    /**
     * Retrieves a list of all users.
     * @return an array of UserPO objects
     */
    public UserPO[] listUsers() {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        return restTemplate.exchange(USERS_LIST_URL, HttpMethod.GET, request, UserPO[].class).getBody();
    }

    /**
     * Adds a new user.
     * @param user a UserPO object representing the new user
     * @return the newly created UserPO object
     */
    public UserPO addUser(UserPO user) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<UserPO> request = new HttpEntity<>(user, headers);
        Map<String, String> urlParameters = new HashMap<>();
        return restTemplate.exchange(USERS_ADD_URL, HttpMethod.POST, request, UserPO.class, urlParameters).getBody();
    }

    /**
     * Deletes a user by ID.
     * @param userId the ID of the user to delete
     * @return A ResponseEntity object representing the status of the delete operation.
     */
    public ResponseEntity<Void> removeUser(int userId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        // Loading values in the URL
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("userId", String.valueOf(userId));
        return restTemplate.exchange(USERS_DELETE_URL, HttpMethod.DELETE, request, Void.class, urlParameters);
    }

    /**
     * Updates a user's icon.
     * @param user a UserPO object representing the updated user
     * @return the updated UserPO object
     */
    public UserPO editIcon(UserPO user) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<UserPO> request = new HttpEntity<>(user, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("userId", String.valueOf(user.getUserId()));
        return restTemplate.exchange(USERS_EDIT_URL, HttpMethod.PUT, request, UserPO.class, urlParameters).getBody();
    }

    /**
     * Updates a user's bio.
     * @param user a UserPO object representing the updated user
     * @return the updated UserPO object
     */
    public UserPO editBio(UserPO user) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<UserPO> request = new HttpEntity<>(user, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("userId", String.valueOf(user.getUserId()));
        return restTemplate.exchange(USERS_EDIT_URL, HttpMethod.PUT, request, UserPO.class, urlParameters).getBody();
    }

    /**
     * Updates the password of a user in the database.
     *
     * @param user the UserPO object containing the updated user information
     * @return the updated UserPO object
     */
    public UserPO editPassword(UserPO user) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<UserPO> request = new HttpEntity<>(user, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("userId", String.valueOf(user.getUserId()));
        return restTemplate.exchange(USERS_EDIT_URL + "/password", HttpMethod.PUT, request, UserPO.class, urlParameters).getBody();
    }

    /**
     * Validates the user's login credentials against the database.
     *
     * @param username the username of the user to be validated
     * @param password the password of the user to be validated
     * @return a ResponseEntity containing the UserPO object if the validation is successful, or an error message if not
     */
    public ResponseEntity<UserPO> validate(String username, String password) {
        UserPO validateMe = new UserPO();
        validateMe.setUsername(username);
        validateMe.setPassword(password);
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<UserPO> request = new HttpEntity<>(validateMe, headers);
        return restTemplate.exchange(USERS_LOGIN_URL, HttpMethod.POST, request, UserPO.class);
    }

    /**
     * Retrieves a user's information from the database using their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the UserPO object for the specified user
     */
    public UserPO getUserByID(int id) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<UserPO> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("userId", String.valueOf(id));
        return restTemplate.exchange(USERS_EDIT_URL, HttpMethod.GET, request, UserPO.class, urlParameters).getBody();
    }
}