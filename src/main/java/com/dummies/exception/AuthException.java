package com.dummies.exception;


/**
 * Base class for authentication and authorization exceptions.
 */
public class AuthException extends Exception {
    public AuthException(String s) {
        super(s);
    }
}
