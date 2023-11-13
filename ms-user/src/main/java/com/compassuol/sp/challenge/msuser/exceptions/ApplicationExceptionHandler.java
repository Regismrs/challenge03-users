package com.compassuol.sp.challenge.msuser.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

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

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ExceptionsResponse handleExceptionsBadRequest(DataIntegrityViolationException e){
        return new ExceptionsResponse(
                409,
                "CONFLICT",
                e.getMostSpecificCause().getMessage(),
                new ArrayList<>()
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ExceptionsResponse handleAllException(Exception exception){
        exception.printStackTrace();
        if (exception instanceof BadCredentialsException) {
            return new ExceptionsResponse(
                    401,
                    "The username or password is incorrect",
                    exception.getMessage(),
                    new ArrayList<>());
        } else if (exception instanceof SignatureException) {

            return new ExceptionsResponse(
                    403,
                    "The JWT signature is invalid",
                    exception.getMessage(),
                    new ArrayList<>());
        } else if (exception instanceof ExpiredJwtException) {

            return new ExceptionsResponse(
                    403,
                    "The JWT token has expired",
                    exception.getMessage(),
                    new ArrayList<>());
        } else {
            return new ExceptionsResponse(
                    500,
                    "Internal Server Error",
                    "",
                    new ArrayList<>());
        }
    }
}
