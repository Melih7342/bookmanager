package com.melih.bookmanager.repository.user;

import com.melih.bookmanager.api.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);

    void save(User user);
}
