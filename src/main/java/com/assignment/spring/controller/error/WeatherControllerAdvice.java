package com.assignment.spring.controller.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ehsan Sh
 */


@ControllerAdvice
@Slf4j
public class WeatherControllerAdvice {
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> handleRequestBody(HttpClientErrorException ex) {
        String errorMessages= ex.getMessage();

        log.error("errorMessage : {} ", errorMessages);
        return new ResponseEntity<>(errorMessages,ex.getStatusCode() );

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleRequestBody(Exception ex) {
        String errorMessages= ex.getMessage();

        log.error("errorMessage : {} ", errorMessages);
        return new ResponseEntity<>(errorMessages,HttpStatus.INTERNAL_SERVER_ERROR );

    }
}

