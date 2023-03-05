package com.harmonia.backend.repository;

import com.harmonia.backend.domain.PublicMessage;
import org.springframework.data.repository.CrudRepository;

/**
 * This interface extends the Spring Data CrudRepository and provides
 * basic CRUD (Create, Read, Update, Delete) operations for the PublicMessage entity.
 * It defines the methods to interact with the public_messages table in the database.
 *
 * @author Harmonia Team
 * @version 1.0
 */
public interface PublicMessageRepository extends CrudRepository<PublicMessage, Long> {
}
