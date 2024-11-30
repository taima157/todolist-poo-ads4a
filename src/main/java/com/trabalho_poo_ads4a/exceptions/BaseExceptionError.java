package com.trabalho_poo_ads4a.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseExceptionError extends RuntimeException {

    private HttpStatus statusCode;
    private String error;

    BaseExceptionError() {
        super();
        this.statusCode = null;
        this.error = null;
    }

    BaseExceptionError(String message) {
        super(message);
        this.statusCode = null;
        this.error = null;
    }

    BaseExceptionError(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.error = null;
    }

    BaseExceptionError(HttpStatus statusCode, String message, String error) {
        super(message);
        this.statusCode = statusCode;
        this.error = error;
    }

}
