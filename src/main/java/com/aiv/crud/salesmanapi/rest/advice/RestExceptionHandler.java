package com.aiv.crud.salesmanapi.rest.advice;

import com.aiv.crud.salesmanapi.exception.DefaultExceptionResponse;
import com.aiv.crud.salesmanapi.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DefaultExceptionResponse> entityNotFoundExceptionHandler(EntityNotFoundException ex) {
        var response = DefaultExceptionResponse.builder()
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
