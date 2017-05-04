package com.car.controllers;

import com.car.exceptions.CarNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 11/04/2017
 */
//@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(Exception.class)
    public String getException(Model model) {
        model.addAttribute("view", "/error/500");
        return "/base-layout";
    }

    @ExceptionHandler(CarNotFoundException.class)
    public String getNotFound(Model model) {
        model.addAttribute("view", "/error/not-found");
        return "/base-layout";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String notFound(Model model) {
        model.addAttribute("view", "/error/not-found");
        return "/base-layout";
    }

}
