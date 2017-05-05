package com.car.controllers;

import com.car.exceptions.CarNotFoundException;
import com.car.exceptions.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 11/04/2017
 */
@ControllerAdvice
public class GlobalExceptionController {

//    @ExceptionHandler(Exception.class)
//    public String getException(Model model) {
//        model.addAttribute("view", "/error/500");
//        return "/base-layout";
//    }

    @ExceptionHandler(CarNotFoundException.class)
    public String getNotFound(Model model, CarNotFoundException e) {
        model.addAttribute("view", "/error/404");
        model.addAttribute("message", e.getMessage());
        return "/base-layout";
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public String entityNotFound(Model model, EntityNotFoundException e) {
        model.addAttribute("view", "/error/404");
        model.addAttribute("message", e.getMessage());
        return "/base-layout";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String notFound(Model model) {
        model.addAttribute("view", "/error/404");
        return "/base-layout";
    }

    @ExceptionHandler(IllegalAccessException.class)
    public String noAccess(Model model) {
        model.addAttribute("view", "/error/noAccess");
        return "/base-layout";
    }

}
