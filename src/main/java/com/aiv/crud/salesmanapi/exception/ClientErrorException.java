package com.aiv.crud.salesmanapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ClientErrorException extends RuntimeException {
    public ClientErrorException(String message) {
        super(message);
    }
}
