package com.melih.bookmanager.service;

import com.melih.bookmanager.api.model.User;
import com.melih.bookmanager.exception.User.BadCredentialsException;
import com.melih.bookmanager.exception.User.DisabledAccountException;
import com.melih.bookmanager.exception.User.UsernameAlreadyExistsException;
import com.melih.bookmanager.repository.user.InMemoryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final InMemoryUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(String username, String password) {
        if(userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException();
        }
        String hashedPassword = passwordEncoder.encode(password);

        User user = new User(username, hashedPassword);

        userRepository.save(user);
    }

    public void login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(BadCredentialsException::new);

        if (!user.isActive()) {
            throw new DisabledAccountException();
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException();
        }
    }

    public void deactivateAccount(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        if (passwordEncoder.matches(password, user.getPassword())) {
            user.setActive(false);
            userRepository.save(user);
        }
    }

    public void changePassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(BadCredentialsException::new);

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadCredentialsException();
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
