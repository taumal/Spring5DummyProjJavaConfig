package com.dummies.exception;

import org.apache.commons.lang.exception.NestableException;

/**
 * @author taumal
 * @since 1/31/18 4:22 PM
 */
public class WebSecurityException extends NestableException {
    public WebSecurityException() {
        this("Insufficient privilege");
    }

    public WebSecurityException(String s) {
        super(s);
    }
}
