package com.melih.bookmanager.repository.book;

import com.melih.bookmanager.api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

}
