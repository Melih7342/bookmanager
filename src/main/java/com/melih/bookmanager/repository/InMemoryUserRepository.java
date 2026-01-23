package com.melih.bookmanager.repository;

import com.melih.bookmanager.api.model.User;
import com.melih.bookmanager.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements BookRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }
}
