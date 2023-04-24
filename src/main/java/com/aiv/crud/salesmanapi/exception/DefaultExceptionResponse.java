package com.aiv.crud.salesmanapi.exception;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder(toBuilder = true)
@Data
public class DefaultExceptionResponse {

    private String message;
    private LocalDateTime timestamp;
    private int status;

}
