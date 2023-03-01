package com.harmonia.backend.repository;

import com.harmonia.backend.domain.ServerMember;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServerMemberRepository extends CrudRepository<ServerMember, Long> {
    List<ServerMember> listServersByMemberId(Long memberId);
}
