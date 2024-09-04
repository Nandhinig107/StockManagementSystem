package com.cts.exception;

@SuppressWarnings("serial")
public class InvalidChoiceException extends RuntimeException {
    public InvalidChoiceException(String message) {
        super(message);
    }
}
