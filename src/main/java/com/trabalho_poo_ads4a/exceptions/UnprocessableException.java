package com.trabalho_poo_ads4a.exceptions;

import org.springframework.http.HttpStatus;

public class UnprocessableException extends BaseExceptionError{

    public UnprocessableException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }

    public UnprocessableException(String message, String error) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message, error);
    }

}
