package com.melih.bookmanager.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Data
@AllArgsConstructor
public class Book {
    private String ISBN;
    private String title;
    private String author;
    private int pages;
}
