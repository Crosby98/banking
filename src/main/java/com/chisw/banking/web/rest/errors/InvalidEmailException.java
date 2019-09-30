package com.chisw.banking.web.rest.errors;

import org.springframework.http.HttpStatus;

public class InvalidEmailException extends DomainException {

    public InvalidEmailException(String email) {
        super("Invalid patter for email: " + email, HttpStatus.BAD_REQUEST);
    }
}
