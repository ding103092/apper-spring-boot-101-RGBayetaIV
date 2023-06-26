package com.apper.exception;

public class NoRegisteredAccountsException extends Exception {
    public NoRegisteredAccountsException(String message) {
        super(message);
    }
}
