package com.melih.bookmanager.exception;

import com.melih.bookmanager.exception.Book.BookAlreadyExistsException;
import com.melih.bookmanager.exception.Book.BookNotFoundException;
import com.melih.bookmanager.exception.User.BadCredentialsException;
import com.melih.bookmanager.exception.User.DisabledAccountException;
import com.melih.bookmanager.exception.User.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex) {
        return buildExceptionResponseBody(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<Object> handleBookAlreadyExistsException(BookAlreadyExistsException ex) {
        return buildExceptionResponseBody(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentials(BadCredentialsException ex) {
        return buildExceptionResponseBody(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex) {
        return buildExceptionResponseBody(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DisabledAccountException.class)
    public ResponseEntity<Object> handleDisabledAccountException(DisabledAccountException ex) {
        return buildExceptionResponseBody(ex.getMessage(), HttpStatus.CONFLICT);
    }

    private ResponseEntity<Object> buildExceptionResponseBody(String message, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("message", message);

        return new ResponseEntity<>(body, status);
    }

}