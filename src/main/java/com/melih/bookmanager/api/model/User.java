package com.melih.bookmanager.api.model;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String username;
    private String password;
    private boolean active;
    private List<Book> currentlyReading = new ArrayList<>();
    private List<Book> readBooks =  new ArrayList<>();

    public User(String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.active = true;
    }
}
