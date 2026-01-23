package com.melih.bookmanager.api.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String username;
    private String password;
    private List<Book> currentlyReading;

    public User(String username) {
        this.id = UUID.randomUUID();
        this.username = username;
    }
}
