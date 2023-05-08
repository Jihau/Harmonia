package com.harmonia.client;

import com.harmonia.po.FriendPo;
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

/** This class provides a client to interact with the friend API of the Harmonia application.
 * The client provides methods to retrieve all friends, add a new friend, and remove a friend.
 *
 * @author Harmonia Team
 * @version 2.0
 */
public class FriendClient {
    protected RestTemplate restTemplate;

    public FriendClient() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    /** Retrieves all friends from the server.
     *
     * @return An array of {@link FriendPo} objects representing all friends in the system.
     */
    public FriendPo[] listFriends() {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        return restTemplate.exchange(FRIEND_LIST_BY_USERID_URL, HttpMethod.GET, request, FriendPo[].class).getBody();
    }

    /** Adds a new friend to the server.
     *
     * @param friendPo The {@link FriendPo} object representing the friend to be added.
     * @return The {@link FriendPo} object representing the friend that was added.
     */
    public FriendPo addFriend(FriendPo friendPo) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<FriendPo> request = new HttpEntity<>(friendPo, headers);
        Map<String, String> urlParameters = new HashMap<>();
        return restTemplate.exchange(FRIEND_ADD_URL, HttpMethod.POST, request, FriendPo.class, urlParameters).getBody();
    }

    /** Removes a friend from the server.
     *
     * @param userId The {@link FriendPo} object representing the friend to be removed.
     * @return The {@link FriendPo} object representing the friend that was removed.
     */
    public ResponseEntity<Void> removeFriend(int userId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("userId", String.valueOf(userId));
        return restTemplate.exchange(FRIEND_REMOVE_URL, HttpMethod.POST, request, Void.class, urlParameters);

    }
}
