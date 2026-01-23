package com.melih.bookmanager.repository;

import com.melih.bookmanager.api.model.User;

import java.util.Optional;

public interface BookRepository {
    Optional<User> findByUsername(String username);

    void save(User user);
}
