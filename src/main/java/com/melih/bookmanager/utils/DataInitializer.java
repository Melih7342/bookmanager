package com.melih.bookmanager.utils;

import com.melih.bookmanager.api.model.Book;
import com.melih.bookmanager.api.model.User;
import com.melih.bookmanager.repository.book.BookRepository;
import com.melih.bookmanager.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@Slf4j // Lombok for Logging
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            UserRepository userRepository,
            BookRepository bookRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                log.info("Initialize test data...");

                // 1. Create Admin & User
                User admin = new User("admin", passwordEncoder.encode("admin"));
                admin.setRole("ROLE_ADMIN");
                admin.setActive(true);

                User user = new User("user", passwordEncoder.encode("user"));
                user.setRole("ROLE_USER");
                user.setActive(true);

                userRepository.saveAll(List.of(admin, user));

                // 2. Add a few books
                bookRepository.saveAll(List.of(
                        new Book("978-0132350884", "Clean Code", "Robert C. Martin", 464),
                        new Book("978-0596007126", "Head First Design Patterns", "Eric Freeman", 694),
                        new Book("978-0134685991", "Effective Java", "Joshua Bloch", 416)
                ));

                log.info("### Demo-Data created: admin/admin & user/user ###");
            }
        };
    }
}
