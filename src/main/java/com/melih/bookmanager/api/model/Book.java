package com.melih.bookmanager.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Book {
    @Id
    private String ISBN;
    private String title;
    private String author;
    private int pages;
}
