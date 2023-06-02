package com.aiv.crud.salesmanapi.rest.advice;

import com.aiv.crud.salesmanapi.exception.ClientErrorException;
import com.aiv.crud.salesmanapi.exception.DefaultExceptionResponse;
import com.aiv.crud.salesmanapi.exception.EntityNotFoundException;
import com.aiv.crud.salesmanapi.exception.ValidationExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DefaultExceptionResponse> entityNotFoundExceptionHandler(EntityNotFoundException ex) {
        var response = DefaultExceptionResponse.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(ClientErrorException.class)
    public ResponseEntity<DefaultExceptionResponse> clientErrorExceptionHandler(ClientErrorException ex) {
        var response = DefaultExceptionResponse.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ValidationExceptionResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
//
//        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
//
//        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
//        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
//
//        var response = ValidationExceptionResponse.builder()
//                .message("Ocorreu um erro na validação dos campos")
//                .timestamp(LocalDateTime.now())
//                .fields(fields)
//                .fieldsMessage(fieldsMessage)
//                .status(HttpStatus.BAD_REQUEST.value())
//                .build();
//        return ResponseEntity.status(response.getStatus()).body(response);
//
//    }

    @ExceptionHandler({CompletionException.class, InterruptedException.class } )
    public ResponseEntity<ValidationExceptionResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        var response = ValidationExceptionResponse.builder()
                .message("Ocorreu um erro na validação dos campos")
                .timestamp(LocalDateTime.now())
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(response.getStatus()).body(response);

    }

}
