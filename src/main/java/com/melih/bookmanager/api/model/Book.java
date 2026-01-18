package com.melih.bookmanager.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Book {
    private String ISBN;
    private String title;
    private String author;
    private int pages;

    public Book(String ISBN, String title, String author, int pages) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    // Getters
    public String getISBN() {return ISBN;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public int getPages() {return pages;}

    // Setters
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setPages(int pages) {this.pages = pages;}

    // For a quick overview
    public String toString() {
        return "ISBN: " + ISBN + "\nTitle: " + title + "\nAuthor: " + author + "\nPages: " + pages;
    }

}
