package com.car.controllers;

import com.car.areas.cars.models.viewModels.CarViewModel;
import com.car.areas.cars.services.CarService;
import com.car.areas.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private CarService carService;

    @ModelAttribute("userCars")
    public Set<CarViewModel> userCars(HttpSession httpSession) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = addUserIdToSession(httpSession, (User) principal);
        Set<CarViewModel> carViewModels = this.carService.getAllCarsByUser(userId);
        return carViewModels;
    }

    private long addUserIdToSession(HttpSession httpSession, User principal) {
        long userId = principal.getId();
        httpSession.setAttribute("userId", userId);
        if (httpSession.getAttribute("activeCar") != null) {
            httpSession.removeAttribute("activeCar");
        }
        return userId;
    }

    //TODO CREATE landing page. home should be accessible only by logged in user
    @GetMapping("/")
    public String getHomePage(Model model, HttpSession httpSession) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        addUserIdToSession(httpSession, (User) principal);

        model.addAttribute("title", "Home Page");
        model.addAttribute("view", "fragments/empty");
        return "base-layout";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "error";
    }
}
