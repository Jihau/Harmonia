package com.harmonia.backend.repository;

import com.harmonia.backend.domain.DirectMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DirectMessageRepository extends CrudRepository<DirectMessage, Long> {
    List<DirectMessage> listDMsByRecipientId(Long recipientId);
}
