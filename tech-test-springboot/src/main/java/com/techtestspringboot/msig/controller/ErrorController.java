package com.techtestspringboot.msig.controller;

import com.techtestspringboot.msig.dto.WebResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WebResponse<String>> constraintViolationException(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<String>builder()
                        .responseCode(HttpStatus.BAD_REQUEST.value())
                        .responseMessage(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<String>> responseStatusException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(WebResponse.<String>builder()
                        .responseCode(exception.getStatusCode().value())
                        .responseMessage(exception.getReason())
                        .build());
    }

}
