package com.example.studentmanagementsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;


@AllArgsConstructor
@Data
public class ApiException {
    private final String message;
    private final Throwable throwable;
    private  final HttpStatus httpStatus;
    private String details;
    private Date timestamp;
}
