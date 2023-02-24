package com.harmonia.backend.repository;

import com.harmonia.backend.domain.Channel;
import com.harmonia.backend.domain.DirectMessage;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRepository extends CrudRepository<Channel, Long> {
}
