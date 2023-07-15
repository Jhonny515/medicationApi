package com.jhonny.medicationApi.infra.exceptions.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Getter @Setter @Builder
public class ApiError {
    private Timestamp timestamp;
    private HttpStatus status;
    private String error;
    private String path;

    public ApiError(Timestamp timestamp, HttpStatus status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }
}
