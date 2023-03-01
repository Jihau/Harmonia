package com.harmonia.backend.repository;

import com.harmonia.backend.domain.PublicMessage;
import com.harmonia.backend.domain.ServerMembers;
import org.springframework.data.repository.CrudRepository;

public interface ServerMembersRepository extends CrudRepository<ServerMembers, Long> {
}
