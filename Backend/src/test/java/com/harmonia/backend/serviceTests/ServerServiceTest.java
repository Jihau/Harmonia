package com.harmonia.backend.serviceTests;

import com.harmonia.backend.domain.Server;
import com.harmonia.backend.repository.ServerRepository;
import com.harmonia.backend.service.ServerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains JUnit tests for the {@link ServerService} class.
 * @author Harmonia Team
 */
public class ServerServiceTest {
    @Mock
    private ServerRepository serverRepository;

    @InjectMocks
    private ServerService serverService;

    /**
     * This method tests the functionality of the addServer method of the {@link ServerService} class.
     */
    @Test
    public void testAddServer() {
        Server server = new Server();
        server.setServerName("JihausTestServer");
        server.setServerCategory("Gaming");
        server.setOwnerId(1L);
        when(serverRepository.save(server)).thenReturn(server);
        Server savedServer = serverService.addServer(server);
        assertEquals(server, savedServer);
    }

    /**
     * This method tests the functionality of the listServers method of the {@link ServerService} class.
     */
    @Test
    public void testListServers() {
        List<Server> servers = new ArrayList<>();
        Server server1 = new Server();
        server1.setServerName("JihausTestServer1");
        server1.setServerCategory("Music");
        server1.setOwnerId(1L);
        servers.add(server1);
        Server server2 = new Server();
        server2.setServerName("JihausTestServer2");
        server2.setServerCategory("Gaming");
        server2.setOwnerId(1L);
        servers.add(server2);
        when(serverRepository.findAll()).thenReturn(servers);
        Iterable<Server> returnedServers = serverService.listServers();
        assertArrayEquals(servers.toArray(), ((List<Server>) returnedServers).toArray());
        assertEquals(2, ((List<Server>) returnedServers).size());
    }

    /**
     * This method tests the functionality of the deleteServer method of the {@link ServerService} class.
     */
    @Test
    public void testDeleteServer() {
        Long serverId = 1L;
        serverService.deleteServer(serverId);
        verify(serverRepository, times(1)).deleteById(serverId);
    }

    /**
     * This method tests the functionality of the editServerName method of the {@link ServerService} class.
     */
    @Test
    public void testEditServerName() {
        Long serverId = 1L;
        String serverName = "JihausTestServer3";
        String serverCategory = "Legos";

        Server server = new Server();
        server.setServerName("JihausTestServer3 updated");
        server.setServerCategory("Legos");
        server.setOwnerId(1L);

        when(serverRepository.findById(serverId)).thenReturn(Optional.of(server));
        when(serverRepository.save(server)).thenReturn(server);

        serverService.editServerName(serverId, serverName, serverCategory);

        verify(serverRepository, times(1)).findById(serverId);
        verify(serverRepository, times(1)).save(server);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}
