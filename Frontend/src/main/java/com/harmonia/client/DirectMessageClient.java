package com.harmonia.client;

import com.harmonia.po.DMessagePO;
import com.harmonia.po.MessagePO;
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
 * A client class for accessing the Direct Message endpoints of the Harmonia API.
 *
 *
 * @author Harmonia Team
 * @version 1.0
 */
public class DirectMessageClient {

    /**
     * Creates a new instance of DirectMessageClient.
     */
    protected RestTemplate restTemplate;

    public DirectMessageClient() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    /**
     * Returns all messages for the current user.
     *
     * @return an array of MessagePO objects representing the user's messages
     */
    public MessagePO[] getAllMessages() {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<MessagePO[]> response = restTemplate.exchange(DM_GETALL_URL, HttpMethod.GET, request, MessagePO[].class);
        return response.getBody();
    }

    /**
     * Returns all messages sent to a given user.
     *
     * @param userId the ID of the recipient user
     * @return a ResponseEntity containing an array of MessagePO objects representing the user's messages
     */
    public ResponseEntity<MessagePO[]> getMessagesByRecipientID(int userId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<MessagePO> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("recipientId", String.valueOf(userId));

        return restTemplate.exchange(DM_GET_R_ID, HttpMethod.GET, request, MessagePO[].class, urlParameters);
    }

    /**
     * Adds a new message to the system.
     *
     * @param newMessage a MessagePO object representing the message to be added
     * @return a ResponseEntity containing the added MessagePO object
     */
    public ResponseEntity<MessagePO> addMessage(MessagePO newMessage) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<MessagePO> request = new HttpEntity<>(newMessage, headers);
        Map<String, String> urlParameters = new HashMap<>();

        return restTemplate.exchange(DM_ADD_URL, HttpMethod.POST, request, MessagePO.class, urlParameters);
    }

    /**
     * Removes a message from the system.
     *
     * @param removeMe a MessagePO object representing the message to be removed
     * @return a ResponseEntity indicating success or failure of the operation
     */
    public ResponseEntity<?> removeMessage(DMessagePO removeMe) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<DMessagePO> request = new HttpEntity<>(removeMe, headers);
        Map<String, String> urlParameters = new HashMap<>();
        return restTemplate.exchange(DM_ADD_URL, HttpMethod.DELETE, request, String.class, urlParameters);
    }

    /**
     * Edits an existing message in the system.
     *
     * @param message a MessagePO object representing the message to be edited
     * @return a ResponseEntity indicating success or failure of the operation
     */
    public ResponseEntity<?> editMessage(DMessagePO message) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<DMessagePO> request = new HttpEntity<>(message, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("DmessageId", String.valueOf(message.getDmessageId()));
        return restTemplate.exchange(DM_EDIT_URL, HttpMethod.PUT, request, String.class, urlParameters);
    }

/**
 * Returns all messages sent by a given user.
 *
 * @param authorId the ID of the author user
 * @return a ResponseEntity containing an array of
 */
    public ResponseEntity<MessagePO[]> getMessagesByAuthorId(long authorId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<MessagePO> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("authorId", String.valueOf(authorId));
        return restTemplate.exchange(DM_GET_A_ID, HttpMethod.GET, request, MessagePO[].class, urlParameters);
    }

    /**
     * Returns all messages sent by a given user.
     *
     * @param authorId the ID of the author user
     * @param recipientId the ID of the recipient user
     * @return a ResponseEntity containing an array of
     */
    public ResponseEntity<DMessagePO[]> listConversation(long authorId, long recipientId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<Void> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("authorId", String.valueOf(authorId));
        urlParameters.put("recipientId", String.valueOf(recipientId));
        return restTemplate.exchange(DM_GET_CONVERSATION, HttpMethod.GET, request, DMessagePO[].class, urlParameters);
    }
}
