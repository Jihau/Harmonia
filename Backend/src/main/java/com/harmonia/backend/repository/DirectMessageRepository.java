package com.harmonia.backend.repository;

import com.harmonia.backend.domain.DirectMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * This interface extends the Spring Data CrudRepository interface, and provides methods for
 * accessing and manipulating DirectMessage objects in the database.
 *
 * @author Harmonia Team
 * @version 1.0
 */

public interface DirectMessageRepository extends CrudRepository<DirectMessage, Long> {

    /**
     * Returns a list of all DirectMessages with the specified recipient ID.
     *
     * @param recipientId the ID of the recipient to filter by
     * @return a list of DirectMessage objects that match the given recipient ID
     */
    List<DirectMessage> listDMsByRecipientId(Long recipientId);

    /**
     * Returns a list of all DirectMessages with the specified author ID.
     *
     * @param authorId the ID of the author to filter by
     * @return a list of DirectMessage objects that match the given author ID
     */
    List<DirectMessage> listDMsByAuthorId(Long authorId);

    List<DirectMessage> listConversation(Long authorId, Long recipientId);

}
