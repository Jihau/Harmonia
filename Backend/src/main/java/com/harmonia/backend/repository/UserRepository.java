package com.harmonia.backend.repository;

import com.harmonia.backend.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> listUsersByUserName(String username);
}
