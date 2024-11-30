package com.trabalho_poo_ads4a.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseExceptionError {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public NotFoundException(String message, String error) {
        super(HttpStatus.NOT_FOUND, message, error);
    }

}
