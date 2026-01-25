package com.melih.bookmanager.repository.user;

import com.melih.bookmanager.api.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    public boolean existsByUsername(String username) {
        return users.containsKey(username);
    }

    public void delete(User user) {
        users.remove(user.getUsername());
    }
}
