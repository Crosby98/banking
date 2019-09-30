package com.chisw.banking.web.rest.errors;

import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends DomainException {

    public EmailNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
