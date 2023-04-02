package com.harmonia.backend.repository;

import com.harmonia.backend.domain.Friend;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for managing Friend entities.
 * Provides basic CRUD operations and some custom methods.
 *
 * @author Harmonia Team
 * @version 1.0
 */
public interface FriendRepository extends CrudRepository<Friend, Long> {
    List<Friend> listFriendsByUserId(Long userId);

    Friend findByUserIdAndFriendId(Long userId, Long friendId);
}
