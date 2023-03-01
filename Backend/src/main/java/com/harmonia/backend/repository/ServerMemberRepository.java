package com.harmonia.backend.repository;

import com.harmonia.backend.domain.ServerMember;
import org.springframework.data.repository.CrudRepository;

public interface ServerMemberRepository extends CrudRepository<ServerMember, Long> {
}
