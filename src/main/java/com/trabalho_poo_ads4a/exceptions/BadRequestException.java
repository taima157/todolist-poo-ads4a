package com.trabalho_poo_ads4a.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseExceptionError {

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public BadRequestException(String message, String error) {
        super(HttpStatus.BAD_REQUEST, message, error);
    }

}
