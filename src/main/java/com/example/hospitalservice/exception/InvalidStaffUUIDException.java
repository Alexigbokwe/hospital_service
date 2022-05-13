package com.example.hospitalservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidStaffUUIDException extends RuntimeException {
    public InvalidStaffUUIDException(String message) {
        super(message);
    }
}
