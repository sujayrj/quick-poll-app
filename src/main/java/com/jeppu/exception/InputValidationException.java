package com.jeppu.exception;

import org.springframework.web.bind.annotation.ResponseStatus;


public class InputValidationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public InputValidationException() {
        super();
    }

    public InputValidationException(String message) {
        super(message);
    }

    public InputValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
