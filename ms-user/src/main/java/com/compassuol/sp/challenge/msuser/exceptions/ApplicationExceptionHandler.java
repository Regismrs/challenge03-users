package com.compassuol.sp.challenge.msuser.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    // entity validations
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public ExceptionsResponse handleExceptionsBadRequest(ConstraintViolationException e){

        List<FieldErrorRecord> details = new ArrayList<>();

        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            details.add(new FieldErrorRecord(field, message));

        }

        return new ExceptionsResponse(
                400,
                HttpStatus.BAD_REQUEST.name(),
                "Invalid request",
                details
        );
    }

    // dtos validations
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ExceptionsResponse handleExceptionsBadRequest(MethodArgumentNotValidException e){
        List<FieldErrorRecord> details = new ArrayList<>();

        for (FieldError violation : e.getBindingResult().getFieldErrors()) {
            String field = violation.getField().replace("Request", "");
            String message = violation.getDefaultMessage();
            details.add(new FieldErrorRecord(field, message));
        }

        return new ExceptionsResponse(
                400,
                HttpStatus.BAD_REQUEST.name(),
                "Invalid request",
                details
        );
    }

    // enum invalid value
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ExceptionsResponse handleBusinessException(HttpMessageNotReadableException e){
        String usefullMessage = e.getMessage().split("String")[1];
        return new ExceptionsResponse(
                400,
                HttpStatus.BAD_REQUEST.name(),
                usefullMessage,
                new ArrayList<>()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ExceptionsResponse handleExceptionNotFound(NotFound e){
        return new ExceptionsResponse(
                404,
                "Not Found",
                e.getMessage(),
                new ArrayList<>()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ExceptionsResponse handleBusinessException(SQLIntegrityConstraintViolationException e){
        return new ExceptionsResponse(
                400,
                "Bad Request",
                e.getMessage(),
                new ArrayList<>()
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ExceptionsResponse handleAllException(){

        return new ExceptionsResponse(
                500,
                "Internal Server Error",
                "Unexpected Error",
                new ArrayList<>()
        );
    }
}
