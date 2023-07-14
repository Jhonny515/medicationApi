package com.jhonny.medicationApi.infra.exceptions;

import com.jhonny.medicationApi.infra.exceptions.utils.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ApiError apiError =
                new ApiError(new Timestamp(new Date().getTime()), HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ NoSuchElementException.class })
    public ResponseEntity<Object> handleNoSuchElement(
            NoSuchElementException ex,
            WebRequest request
    ) {
        ApiError apiError =
                new ApiError(new Timestamp(new Date().getTime()), HttpStatus.NOT_FOUND, ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus()
        );
    }

    @ExceptionHandler({ ClassCastException.class })
    public ResponseEntity<Object> handleClassCast(
            ClassCastException ex,
            WebRequest request
    ) {
        ApiError apiError =
                new ApiError(new Timestamp(new Date().getTime()), HttpStatus.BAD_REQUEST, ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus()
        );
    }
}
