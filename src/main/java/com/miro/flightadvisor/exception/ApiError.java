package com.miro.flightadvisor.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ApiError {

    private HttpStatus status;
    private int errorCode;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, int errorCode, String message, List<String> errors) {
        super();
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, int errorCode, String message, String error) {
        super();
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        errors = Arrays.asList(error);
    }
}