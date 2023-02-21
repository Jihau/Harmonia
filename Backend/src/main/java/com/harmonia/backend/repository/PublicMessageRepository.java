package com.harmonia.backend.repository;

import com.harmonia.backend.domain.PublicMessage;
import org.springframework.data.repository.CrudRepository;

public interface PublicMessageRepository extends CrudRepository<PublicMessage, Long> {
}
