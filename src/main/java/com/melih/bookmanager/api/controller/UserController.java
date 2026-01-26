package com.melih.bookmanager.api.controller;

import com.melih.bookmanager.service.UserService;
import com.melih.bookmanager.utils.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.melih.bookmanager.utils.UserAuthenticationRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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

}
