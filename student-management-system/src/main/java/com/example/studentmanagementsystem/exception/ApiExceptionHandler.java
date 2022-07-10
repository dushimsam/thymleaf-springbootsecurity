package com.example.studentmanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler {

    private HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
    private HttpStatus INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;
    // handling specific exception

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<?> resourceNotFoundHandling(ApiRequestException exception, WebRequest request){
        ApiException exceptionFormat =
                new ApiException(exception.getMessage(),exception, BAD_REQUEST,request.getDescription(false),new Date());
        return new ResponseEntity<>(exceptionFormat, BAD_REQUEST);
    }

    // handling global exception

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
        ApiException exceptionFormat = new ApiException(exception.getMessage(), exception,INTERNAL_SERVER_ERROR,request.getDescription(false),new Date());
        return new ResponseEntity<>(exceptionFormat, INTERNAL_SERVER_ERROR);
    }
}
