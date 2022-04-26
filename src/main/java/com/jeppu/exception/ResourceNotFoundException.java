package com.jeppu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1l;
    private String message;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
