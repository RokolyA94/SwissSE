package com.epam.exception;

public class InvalidCsv extends RuntimeException {

    public InvalidCsv(String message) {
        super(message);
    }
}
