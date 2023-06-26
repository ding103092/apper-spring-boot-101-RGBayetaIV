package com.apper.exception;

public class UsernameAlreadyRegisteredException extends Exception {
    public UsernameAlreadyRegisteredException(String message) {
        super(message);
    }
}
