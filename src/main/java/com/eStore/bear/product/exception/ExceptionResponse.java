package com.eStore.bear.product.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExceptionResponse {

    private HttpStatus status;
    private List<String> errors;
    private LocalDateTime timestamp;
    private String pathUrl;

}
