package com.melih.bookmanager.service;

import com.melih.bookmanager.repository.user.InMemoryUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final InMemoryUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

}
