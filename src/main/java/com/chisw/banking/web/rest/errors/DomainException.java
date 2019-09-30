package com.chisw.banking.web.rest.errors;

import org.springframework.http.HttpStatus;

public class DomainException extends RuntimeException {
    private HttpStatus status;

    public DomainException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
