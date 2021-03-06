package com.car.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 03/05/2017
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid id")
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
