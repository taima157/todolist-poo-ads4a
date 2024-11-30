package com.trabalho_poo_ads4a.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseExceptionError {

    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

    public UnauthorizedException(String message, String error) {
        super(HttpStatus.UNAUTHORIZED, message, error);
    }

}
