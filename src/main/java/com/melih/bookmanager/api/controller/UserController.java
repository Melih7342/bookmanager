package com.melih.bookmanager.api.controller;

import com.melih.bookmanager.api.model.Book;
import com.melih.bookmanager.api.model.User;
import com.melih.bookmanager.repository.user.UserRepository;
import com.melih.bookmanager.service.UserService;
import com.melih.bookmanager.utils.UserChangePasswordRequest;
import com.melih.bookmanager.utils.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.melih.bookmanager.utils.UserAuthenticationRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserAuthenticationRequest request) {
        userService.register(request.getUsername(), request.getPassword());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(request.getUsername())
                .toUri();
        return ResponseEntity.created(location).body("User registered successfully");
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserProfile(username));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserAuthenticationRequest request) {
        userService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("Login successful");
    }

    @PatchMapping("/{username}/deactivate")
    public ResponseEntity<String> deactivate(@PathVariable String username, @RequestBody String password) {
        userService.deactivateAccount(username, password);
        return ResponseEntity.ok("Account " + username + " deactivated");
    }

    @PatchMapping("/{username}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable String username, @RequestBody UserChangePasswordRequest request) {
        userService.changePassword(username, request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.ok("Password changed successfully");
    }

    @GetMapping("{username}/currently-reading")
    public ResponseEntity<List<Book>> getCurrentlyReadingBooks(@PathVariable String username) {
        List<Book> currentlyReading = userService.getUserByUsername(username).getCurrentlyReading();
        return ResponseEntity.ok(currentlyReading);
    }

    @GetMapping("/{username}/read")
    public ResponseEntity<List<Book>> getMarkedAsReadBook(@PathVariable String username) {
        List<Book> readBooks = userService.getUserByUsername(username).getReadBooks();
        return ResponseEntity.ok(readBooks);
    }

    @PostMapping("/currently-reading/{isbn}")
    public ResponseEntity<String> markCurrentlyReadingBook(@PathVariable String isbn, @RequestBody String username) {
        userService.markAsCurrentlyReading(isbn, username);
        return ResponseEntity.ok("Book successfully marked as currently reading");
    }

    @PostMapping("/read/{isbn}")
    public ResponseEntity<String> markReadBook(@PathVariable String isbn, @RequestBody String username) {
        userService.markAsRead(isbn, username);
        return ResponseEntity.ok("Book successfully marked as read");
    }

}
