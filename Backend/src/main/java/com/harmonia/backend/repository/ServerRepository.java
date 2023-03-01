package com.harmonia.backend.repository;

import com.harmonia.backend.domain.Server;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServerRepository extends CrudRepository<Server, Long> {
    List<Server> listServersByServerName(String serverName);

    List<Server> listServersByOwnerId(Long userId);
}
