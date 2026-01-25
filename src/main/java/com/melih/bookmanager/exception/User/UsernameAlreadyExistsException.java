package com.melih.bookmanager.exception.User;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException() {
        super("This username already exists");
    }
}
