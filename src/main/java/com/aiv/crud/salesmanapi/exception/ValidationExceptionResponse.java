package com.aiv.crud.salesmanapi.exception;


import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
public class ValidationExceptionResponse extends DefaultExceptionResponse {

    private String fields;
    private String fieldsMessage;

}
