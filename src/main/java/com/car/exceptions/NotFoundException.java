package com.car.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 13/04/2017
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such virus")
public class NotFoundException extends RuntimeException {

}
