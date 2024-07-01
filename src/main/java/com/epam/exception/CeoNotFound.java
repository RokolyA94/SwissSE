package com.epam.exception;

public class CeoNotFound extends RuntimeException {

    public CeoNotFound(String message) {
        super(message);
    }
}
