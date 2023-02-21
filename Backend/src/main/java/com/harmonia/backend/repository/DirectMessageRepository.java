package com.harmonia.backend.repository;

import com.harmonia.backend.domain.DirectMessage;
import org.springframework.data.repository.CrudRepository;

public interface DirectMessageRepository extends CrudRepository<DirectMessage, Long> {
}
