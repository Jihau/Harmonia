package com.harmonia.backend.repository;

import com.harmonia.backend.domain.ServerMember;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * This interface extends the CrudRepository interface and provides additional methods to interact with the database table
 * storing the ServerMember entities.
 *
 * @author Harmonia Team
 * @version 1.0
 */
public interface ServerMemberRepository extends CrudRepository<ServerMember, Long> {

    /**
     * Retrieves a list of Servers that a specific member has joined.
     *
     * @param memberId the ID of the member whose ServerMembers we want to retrieve.
     * @return a List of servers that the specified member has joined.
     */
    List<ServerMember> listServersByMemberId(Long memberId);


    /**
     * Retrieves a list of ServerMembers that belong to a specific server identified by its ID.
     *
     * @param serverId the ID of the server whose ServerMembers we want to retrieve.
     * @return a List of ServerMembers that belong to the specified server.
     */
    List<ServerMember> listServerMembersByServerId(Long serverId);
}
