package com.harmonia.backend.repository;

import com.harmonia.backend.domain.Friend;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepository extends CrudRepository<Friend, Long> {
    List<Friend> listFriendsByUserId(Long userId);

    Friend findByUserIdAndFriendId(Long userId, Long friendId);
}
