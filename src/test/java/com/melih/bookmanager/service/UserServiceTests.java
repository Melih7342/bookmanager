package com.melih.bookmanager.service;

import com.melih.bookmanager.api.model.User;
import com.melih.bookmanager.repository.user.InMemoryUserRepository;
import com.melih.bookmanager.utils.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class UserServiceTests {
    private InMemoryUserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        this.userRepository = new InMemoryUserRepository();
        this.passwordEncoder = new BCryptPasswordEncoder();

        List<User> testUsers = new ArrayList<>(List.of(
                new User("jeff", passwordEncoder.encode("Spring123")),
                new User("thomas", passwordEncoder.encode("Summer55!")),
                new User("carla10", passwordEncoder.encode("Cheesecake99"))
        ));

        for (User user : testUsers) {
            userRepository.save(user);
        }

        this.userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void givenExistingUsername_whenGetUserByUsername_thenReturnRightUser() {
        // GIVEN
        String username = "jeff";

        // WHEN
        User result = userService.getUserByUsername(username);

        // THEN
        assertThat(result.getUsername()).isEqualTo("jeff");
        assertThat(result.isActive()).isTrue();

        assertThat(result.getCurrentlyReading())
                .isNotNull()
                .isEmpty();

        assertThat(result.getReadBooks())
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void givenNonExistingUsername_whenGetUserByUsername_thenExceptionIsThrown() {
        // GIVEN
        String nonExistingUsername = "IdoNotExist";

        // WHEN & THEN
        assertThatThrownBy(() -> userService.getUserByUsername(nonExistingUsername))
                .isInstanceOf(UsernameNotFoundException.class);
    }

    @Test
    public void givenExistingUsername_whenGetUserProfile_thenReturnRightUserProfile() {
        // GIVEN
        String username = "jeff";

        // WHEN
        UserResponse result = userService.getUserProfile(username);

        // THEN
        assertThat(result.getUsername()).isEqualTo("jeff");

        assertThat(result.getCurrentlyReading())
                .isNotNull()
                .isEmpty();

        assertThat(result.getReadBooks())
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void givenNonExistingUsername_whenGetUserProfile_thenExceptionIsThrown() {
        // GIVEN
        String nonExistingUsername = "IdoNotExist";

        // WHEN & THEN
        assertThatThrownBy(() -> userService.getUserProfile(nonExistingUsername))
                .isInstanceOf(UsernameNotFoundException.class);
    }




}
