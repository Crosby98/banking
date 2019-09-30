package com.chisw.banking.web.rest.errors;

import org.springframework.http.HttpStatus;

public class OperationException extends DomainException {
    public OperationException(String message, HttpStatus status) {
        super(message, status);
    }
}
