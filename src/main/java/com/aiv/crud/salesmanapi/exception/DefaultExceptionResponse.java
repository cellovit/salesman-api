package com.aiv.crud.salesmanapi.exception;


import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class DefaultExceptionResponse {

    private String message;
    private Instant timestamp;
    private int status;

}
