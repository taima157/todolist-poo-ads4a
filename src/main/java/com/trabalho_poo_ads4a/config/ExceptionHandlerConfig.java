package com.trabalho_poo_ads4a.config;

import com.trabalho_poo_ads4a.exceptions.BadRequestException;
import com.trabalho_poo_ads4a.exceptions.NotFoundException;
import com.trabalho_poo_ads4a.exceptions.UnauthorizedException;
import com.trabalho_poo_ads4a.exceptions.UnprocessableException;
import com.trabalho_poo_ads4a.exceptions.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDTO> notFoundException(NotFoundException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity<>(new ErrorResponseDTO(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocessableException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorResponseDTO> unprocessableException(UnprocessableException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity<>(new ErrorResponseDTO(exception), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> badRequestException(BadRequestException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity<>(new ErrorResponseDTO(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseDTO> unauthorizedException(UnauthorizedException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity<>(new ErrorResponseDTO(exception), HttpStatus.UNAUTHORIZED);
    }

}
