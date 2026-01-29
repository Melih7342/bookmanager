package com.melih.bookmanager.service;

import com.melih.bookmanager.api.model.Book;

import com.melih.bookmanager.exception.Book.BookAlreadyExistsException;
import com.melih.bookmanager.exception.Book.BookNotFoundException;
import com.melih.bookmanager.repository.book.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void givenExistingIsbn_whenGetBookByISBN_thenReturnsCorrectBook() {
        // GIVEN
        String isbn = "978-3-16-148410-0";
        Book expectedBook = new Book(isbn, "Der Wind am Ende der Welt", "Franz Kafka", 300);
        when(bookRepository.findById(isbn)).thenReturn(Optional.of(expectedBook));

        // WHEN
        Book result = bookService.getBookByIsbn(isbn);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getISBN()).isEqualTo(isbn);
        verify(bookRepository).findById(isbn);
    }

    @Test
    void givenNonExistentIsbn_whenGetBookByISBN_thenThrowsException() {
        // GIVEN
        String isbn = "999";
        when(bookRepository.findById(isbn)).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThatThrownBy(() -> bookService.getBookByIsbn(isbn))
                .isInstanceOf(BookNotFoundException.class);
    }

    @Test
    void givenNewBook_whenAddingBook_thenBookIsSaved() {
        // GIVEN
        Book newBook = new Book("999-3-16-148410-5", "Faust", "Goethe", 92);
        when(bookRepository.existsById(newBook.getISBN())).thenReturn(false);

        // WHEN
        bookService.addBook(newBook);

        // THEN
        verify(bookRepository, times(1)).save(newBook);
    }

    @Test
    void givenDuplicateIsbn_whenAddingBook_thenThrowsConflictException() {
        // GIVEN
        Book existingBook = new Book("978-3-16-148410-0", "Titel", "Autor", 300);
        when(bookRepository.existsById(existingBook.getISBN())).thenReturn(true);

        // WHEN & THEN
        assertThatThrownBy(() -> bookService.addBook(existingBook))
                .isInstanceOf(BookAlreadyExistsException.class);

        verify(bookRepository, never()).save(any());
    }

    @Test
    void givenExistingIsbn_whenRemoveBook_thenRepositoryDeleteIsCalled() {
        // GIVEN
        String isbn = "978-3-16-148410-0";
        when(bookRepository.existsById(isbn)).thenReturn(true);

        // WHEN
        bookService.removeBook(isbn);

        // THEN
        verify(bookRepository).deleteById(isbn);
    }

    @Test
    void givenNotExistingIsbn_whenRemoveBook_thenExceptionIsThrown() {
        // GIVEN
        String isbn = "999";
        when(bookRepository.existsById(isbn)).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> bookService.removeBook(isbn))
                .isInstanceOf(BookNotFoundException.class);

        verify(bookRepository, never()).deleteById(anyString());
    }

    @Test
    void givenBookWithExistingIsbn_whenUpdateBook_thenBookIsSaved() {
        // GIVEN
        Book updateData = new Book("978-3-16-148410-0", "New title", "New author", 240);
        when(bookRepository.existsById(updateData.getISBN())).thenReturn(true);

        // WHEN
        bookService.updateBook(updateData);

        // THEN
        verify(bookRepository).save(updateData);
    }

    @Test
    void givenBulkOfNewBooks_whenAddBulk_thenAllAreSaved() {
        // GIVEN
        List<Book> newBooks = List.of(
                new Book("1", "T1", "A1", 10),
                new Book("2", "T2", "A2", 20)
        );
        when(bookRepository.existsById(anyString())).thenReturn(false);

        // WHEN
        bookService.addBooksBulk(newBooks);

        // THEN
        verify(bookRepository, times(newBooks.size())).save(any(Book.class));
    }

    @Test
    void givenMixedIsbns_whenRemoveBulk_thenTransactionalBehavior() {
        // GIVEN
        String existing = "1";
        String nonExisting = "999";
        List<String> isbns = List.of(existing, nonExisting);

        when(bookRepository.existsById(existing)).thenReturn(true);
        when(bookRepository.existsById(nonExisting)).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> bookService.removeBooksBulk(isbns))
                .isInstanceOf(BookNotFoundException.class);
    }
}