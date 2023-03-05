package com.harmonia.backend.repository;

import com.harmonia.backend.domain.Channel;
import com.harmonia.backend.domain.DirectMessage;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for managing Channel entities.
 * Provides basic CRUD operations and some custom methods.
 *
 * @author Harmonia Team
 * @version 1.0
 */
public interface ChannelRepository extends CrudRepository<Channel, Long> {
}
