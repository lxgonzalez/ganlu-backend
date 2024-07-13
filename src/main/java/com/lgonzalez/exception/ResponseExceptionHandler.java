package com.lgonzalez.exception;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(),ex.getMessage() == null?"Data No Existed": ex.getMessage()+ " data No Existed",request.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(),"Data Duplicated: "+ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.CONFLICT);
    }

}
