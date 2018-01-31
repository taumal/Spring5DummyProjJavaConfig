package com.dummies.exception;

/**
 * @author taumal
 * @since 12/11/17 10:50 AM
 */
public class InvalidSessionException extends RuntimeException {
    public InvalidSessionException(String s) {
        super(s);
    }
}
