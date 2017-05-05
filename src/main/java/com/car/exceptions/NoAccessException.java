package com.car.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 03/05/2017
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Access")
public class NoAccessException extends RuntimeException {

    public NoAccessException() {
    }

    public NoAccessException(String message) {
        super(message);
    }
}
