package com.melih.bookmanager.exception.User;

public class InactiveAccountException extends RuntimeException {
    public InactiveAccountException() {
        super("This account has been deactivated");
    }
}
