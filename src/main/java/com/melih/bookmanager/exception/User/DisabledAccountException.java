package com.melih.bookmanager.exception.User;

public class DisabledAccountException extends RuntimeException {
    public DisabledAccountException() {
        super("This account has been disabled");
    }
}
