package com.harmonia.client;

import com.harmonia.po.ServerPO;
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
 * This class provides methods for retrieving information about servers using the Harmonia REST API.
 * It uses the RestTemplate class from the Spring Framework to make HTTP requests and receive HTTP responses.
 *
 * @author Harmonia Team
 * @version 1.0
 */
public class ServerClient {

    /**
     * The RestTemplate object used to send HTTP requests.
     */
    protected RestTemplate restTemplate;

    /**
     * Constructs a new instance of the {@code ServerClient} class.
     * Initializes the underlying {@code RestTemplate} instance and sets up a
     * message converter for JSON responses.
     */
    public ServerClient() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    /**
     * Returns an array of all servers in the system.
     *
     * @return an array of {@code ServerPO} objects representing all servers
     * in the system.
     */
    public ServerPO[] listAllServers() {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<ServerPO[]> response = restTemplate.exchange(SERVER_LIST_ALL_URL, HttpMethod.GET, request, ServerPO[].class);
        return response.getBody();
    }

    /**
     * Returns an array of servers that match the specified name.
     *
     * @return an array of {@code ServerPO} objects representing servers with
     * the specified name.
     */
    public ServerPO[] listServersByName() {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<ServerPO[]> response = restTemplate.exchange(SERVER_LIST_BY_NAME_URL, HttpMethod.GET, request, ServerPO[].class);
        return response.getBody();
    }

    /**
     * Returns the server with the specified ID.
     *
     * @param id the ID of the server to retrieve.
     * @return the {@code ServerPO} object representing the server with the
     * specified ID.
     */
    public ServerPO getServerById(int id) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("serverId", String.valueOf(id));

        ResponseEntity<ServerPO> response = restTemplate.exchange(SERVER_LIST_BY_ID_URL, HttpMethod.GET, request, ServerPO.class, urlParameters);
        return response.getBody();
    }

    /**
     * Returns an array of servers that match the specified Category.
     *
     * @return the {@code ServerPO} object representing the server with the
     * specified category.
     */
    public ServerPO[] listServersByCategory(String serverCategory) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<ServerPO[]> response = restTemplate.exchange(SERVER_LIST_BY_CATEGORY_URL.replace("{serverCategory}", serverCategory), HttpMethod.GET, request, ServerPO[].class);
        return response.getBody();
    }
}

