package com.harmonia.backend.repository;

import com.harmonia.backend.domain.Server;
import org.springframework.data.repository.CrudRepository;

public interface ServerRepository extends CrudRepository<Server, Long> {
}
