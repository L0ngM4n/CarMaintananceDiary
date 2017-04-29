package com.car.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(Model model){
       model.addAttribute("title", "Home Page");
       model.addAttribute("view", "fragments/empty");
        return "base-layout";
    }

    @GetMapping("/error")
    public String getErrorPage(){
        return "error";
    }
}
