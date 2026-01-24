package com.melih.bookmanager.service;

import com.melih.bookmanager.api.model.User;
import com.melih.bookmanager.repository.user.InMemoryUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final InMemoryUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void register(String username, String password) {}

    public void login(String username, String password) {}

    public void logout(String username) {}

    public void deleteAccount(String username, String password) {}

    public void changePassword(String username, String oldPassword, String newPassword) {}


}
