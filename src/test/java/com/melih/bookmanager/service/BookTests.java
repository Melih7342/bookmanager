package com.melih.bookmanager.service;

import com.melih.bookmanager.api.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BookTests {
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        // Initial books for test cases
        List<Book> books = List.of(
                new Book("978-3-16-148410-0", "Der Wind am Ende der Welt", "Franz Kafka", 300),
                new Book("978-0-545-01022-1", "Das Schweigen der alten Eiche", "Hermann Hesse", 350),
                new Book("978-1-86197-876-9", "Hafen der verlorenen Träume", "Haruki Murakami", 400),
                new Book("978-3-8273-1542-8", "Die mechanische Rose: Ein Steampunk-Abenteuer", "Albert Camus", 508),
                new Book("978-0-306-40615-7", "Letzter Halt: Wien Hauptbahnhof", "Friedrich Nietzsche", 958)
        );
        this.bookService = new BookService(books);
    }

    @Test
    public void shouldGetBookByISBN() {
        // GIVEN
        String isbn = "978-3-16-148410-0";

        // WHEN
        Optional<Book> result = bookService.getBookByISBN(isbn);

        // THEN
        assertTrue(result.isPresent(), "Book should be found");

        Book bookToFind = new Book("978-3-16-148410-0", "Der Wind am Ende der Welt", "Franz Kafka", 300);
        Book foundBook = result.get();

        assertEquals(bookToFind, foundBook, "Found book equals the book to find");

    }

    @Test
    public void shouldThrowExceptionWhenGetBookByISBN() {
        // GIVEN
        String nonExistentIsbn = "999-3-16-148410-5";
        // WHEN
        Optional<Book> result = bookService.getBookByISBN(nonExistentIsbn);

        // THEN
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnTrueWhenIsbnExists() {
        // GIVEN
        String existingIsbn = "978-3-16-148410-0";

        // WHEN
        boolean exists = bookService.existsByISBN(existingIsbn);

        // THEN
        assertTrue(exists, "Should return true for existing ISBN");
    }

    @Test
    void shouldReturnFalseWhenIsbnNonExistent() {
        // GIVEN
        String nonExistingIsbn = "999-3-16-148410-5";

        // WHEN
        boolean exists = bookService.existsByISBN(nonExistingIsbn);

        // THEN
        assertFalse(exists, "Should return false for non existing ISBN");
    }
}