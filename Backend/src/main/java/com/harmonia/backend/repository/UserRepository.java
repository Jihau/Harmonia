package com.harmonia.backend.repository;

import com.harmonia.backend.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

}
