package com.eStore.bear.product.exception.handler;

import com.eStore.bear.product.exception.CurrencyValidException;
import com.eStore.bear.product.exception.ExceptionResponse;
import com.eStore.bear.product.exception.ProductValidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ProductValidException.class, CurrencyValidException.class})
    ResponseEntity<?> ProductNotValidHandler(Exception exception, ServletWebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setTimestamp(LocalDateTime.now());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST);
        exceptionResponse.setErrors(Arrays.asList(exception.getMessage()));
        exceptionResponse.setPathUrl(request.getDescription(true));

        return new ResponseEntity(exceptionResponse, new HttpHeaders(), exceptionResponse.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream().map(error -> error.getField() + " : " + error.getDefaultMessage()).collect(Collectors.toList());

        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST);
        exceptionResponse.setTimestamp(LocalDateTime.now());
        exceptionResponse.setPathUrl(request.getDescription(false));
        exceptionResponse.setErrors(errors);

        return new ResponseEntity<>(exceptionResponse, headers, exceptionResponse.getStatus());
    }
}
