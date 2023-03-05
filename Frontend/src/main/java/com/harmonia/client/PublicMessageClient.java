package com.harmonia.client;

import com.harmonia.po.PublicMessagePO;
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
 * This class provides a client to interact with the public message API of the Harmonia application.
 * The client provides methods to retrieve all messages, send a new message, edit an existing message,
 * and remove a message.
 *
 * @author Harmonia Team
 * @version 1.0
 */
public class PublicMessageClient {
    protected RestTemplate restTemplate;

    public PublicMessageClient() {
        this.restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    /**
     * Retrieves all public messages from the server.
     *
     * @return An array of {@link PublicMessagePO} objects representing all public messages in the system.
     */
    public PublicMessagePO[] getAllMessages() {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<PublicMessagePO[]> response = restTemplate.exchange(PM_GETALL_URL, HttpMethod.GET, request, PublicMessagePO[].class);
        return response.getBody();
    }

    /**
     * Sends a new public message to the server.
     *
     * @param message The {@link PublicMessagePO} object representing the message to be sent.
     * @return The {@link PublicMessagePO} object representing the message that was sent.
     */
    public PublicMessagePO sendPublicMessage(PublicMessagePO message) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<PublicMessagePO> request = new HttpEntity<>(message, headers);
        ResponseEntity<PublicMessagePO> response = restTemplate.exchange(PM_ADD_URL, HttpMethod.POST, request, PublicMessagePO.class);
        return response.getBody();
    }

    /**
     * Removes a public message from the server.
     *
     * @param pMessageId The ID of the message to be removed.
     * @return A {@link ResponseEntity} object representing the result of the request to remove the message.
     */
    public ResponseEntity<Void> removeMessage(Long pMessageId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("pMessageId", String.valueOf(pMessageId));
        return restTemplate.exchange(PM_REMOVE_URL, HttpMethod.DELETE, request, Void.class, urlParameters);
    }

    /**
     * Edits an existing public message.
     *
     * @param editMe The {@link PublicMessagePO} object representing the message to be edited.
     * @return The {@link PublicMessagePO} object representing the edited message.
     */
    public ResponseEntity<String> editPublicMessage(PublicMessagePO editMe) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<PublicMessagePO> request = new HttpEntity<>(editMe, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("pMessageId", String.valueOf(editMe.getPmessageId()));

        return restTemplate.exchange(PM_EDIT_URL, HttpMethod.PUT, request, String.class, urlParameters);
    }
}
