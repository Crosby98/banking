package com.chisw.banking.web.rest.errors;

import org.springframework.http.HttpStatus;

public class EmailAlreadyUsedException extends DomainException {

    public EmailAlreadyUsedException(String email) {
        super("User with email = " + email + "already exist! ", HttpStatus.BAD_REQUEST);
    }
}
