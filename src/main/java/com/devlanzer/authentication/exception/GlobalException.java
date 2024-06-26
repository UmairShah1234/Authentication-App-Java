package com.devlanzer.authentication.exception;


import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ProblemDetail getException(Exception exception) {
        ProblemDetail problemDetail = null;

        if (exception instanceof BadCredentialsException) {
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            problemDetail.setProperty("access denied", "Authentication Failure");
        }

        if (exception instanceof SignatureException){
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            problemDetail.setProperty("signature error", "Authentication Failure");
        }

        return problemDetail;
    }
}
