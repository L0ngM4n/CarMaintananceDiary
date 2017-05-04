package com.car.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 03/05/2017
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User doesn't have cars. Please add a car")
public class CarsNotFoundException extends RuntimeException {

}
