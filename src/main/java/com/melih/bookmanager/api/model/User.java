package com.melih.bookmanager.api.model;

import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Data
@Getter
public class User {
    private UUID id;
    private String username;
    private String password;
    private List<Book> currentlyReading;
    private boolean active;

    public User(String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.active = true;
    }
}
