package com.melih.bookmanager.service;

import com.melih.bookmanager.api.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BookTests {
    private BookService bookService;

    @BeforeEach
    void setUp() {
        // Initial books for test cases
        List<Book> books = new ArrayList<>(List.of(
                new Book("978-3-16-148410-0", "Der Wind am Ende der Welt", "Franz Kafka", 300),
                new Book("978-0-545-01022-1", "Das Schweigen der alten Eiche", "Hermann Hesse", 350),
                new Book("978-1-86197-876-9", "Hafen der verlorenen Träume", "Haruki Murakami", 400),
                new Book("978-3-8273-1542-8", "Die mechanische Rose: Ein Steampunk-Abenteuer", "Albert Camus", 508),
                new Book("978-0-306-40615-7", "Letzter Halt: Wien Hauptbahnhof", "Friedrich Nietzsche", 958)
        ));
        this.bookService = new BookService(books);
    }

    @Test
    void shouldGetBookByISBN() {
        // GIVEN
        String isbn = "978-3-16-148410-0";

        // WHEN
        Optional<Book> result = bookService.getBookByISBN(isbn);

        // THEN
        assertThat(result.isPresent()).isTrue();

        Book bookToFind = new Book("978-3-16-148410-0", "Der Wind am Ende der Welt", "Franz Kafka", 300);
        Book foundBook = result.get();

        assertThat(bookToFind).isEqualTo(foundBook);

    }

    @Test
    public void shouldThrowExceptionWhenGetBookByISBN() {
        // GIVEN
        String nonExistentIsbn = "999-3-16-148410-5";
        // WHEN
        Optional<Book> result = bookService.getBookByISBN(nonExistentIsbn);

        // THEN
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    void shouldReturnTrueWhenIsbnExists() {
        // GIVEN
        String existingIsbn = "978-3-16-148410-0";

        // WHEN
        boolean exists = bookService.existsByISBN(existingIsbn);

        // THEN
        assertThat(exists).isTrue();
    }

    @Test
    void shouldReturnFalseWhenIsbnNonExistent() {
        // GIVEN
        String nonExistingIsbn = "999-3-16-148410-5";

        // WHEN
        boolean exists = bookService.existsByISBN(nonExistingIsbn);

        // THEN
        assertThat(exists).isFalse();
    }

    @Test
    void shouldAddNewBook() {
        // GIVEN
        int initialSize = bookService.getAllBooks().size();
        Book newBook = new Book("999-3-16-148410-5", "Faust", "Goethe", 92);

        // WHEN
        bookService.addBook(newBook);

        // THEN
        List<Book> books = bookService.getAllBooks();
        assertThat(books)
                .contains(newBook)
                .hasSize(initialSize + 1);
    }

    @Test
    void shouldThrowConflictExceptionWhenIsbnAlreadyExists() {
        // GIVEN
        Book existingBook = new Book("978-3-16-148410-0", "Der Wind am Ende der Welt", "Franz Kafka", 300);

        // WHEN & THEN
        assertThatThrownBy(() -> bookService.addBook(existingBook))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Book already exists")
                .extracting(ex -> ((ResponseStatusException) ex).getStatusCode())
                .isEqualTo(HttpStatus.CONFLICT);
    }
}