package com.melih.bookmanager.utils;

import com.melih.bookmanager.api.model.Book;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String username;
    private List<Book> currentlyReading;
    private List<Book> readBooks;
}
