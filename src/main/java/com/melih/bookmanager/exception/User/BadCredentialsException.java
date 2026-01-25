package com.melih.bookmanager.exception.User;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() {
        super("Authentication failed");
    }
    public BadCredentialsException(String message) {super(message);}
}
