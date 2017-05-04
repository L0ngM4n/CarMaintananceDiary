package com.car.areas.garages.controllers;

import com.car.areas.cars.models.viewModels.CarViewModel;
import com.car.areas.cars.services.CarService;
import com.car.areas.garages.models.bindinngModels.GarageCreateModel;
import com.car.areas.garages.models.viewModels.GarageViewModel;
import com.car.areas.garages.services.GarageService;
import com.car.areas.user.entities.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * 21/04/2017
 */
@Controller
@RequestMapping("garages")
public class GarageController {

    @Autowired
    private Gson gson;

    @Autowired
    private GarageService garageService;

    @Autowired
    private CarService carService;

    @ModelAttribute("userCars")
    public Set<CarViewModel> userCars() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = ((User) principal).getId();
        Set<CarViewModel> carViewModels = this.carService.getAllCarsByUser(userId);
        return carViewModels;
    }


    @GetMapping("")
    public String garage(Model model, HttpSession httpSession, @ModelAttribute GarageCreateModel garageCreateModel) {
        addUserIdToSession(httpSession, (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<GarageViewModel> garages = this.garageService.getAllByUserId((Long) httpSession.getAttribute("userId"));

        model.addAttribute("title", "Garages List");
        model.addAttribute("view", "/fragments/garages-add-list");
        model.addAttribute("garages", garages);

        return "garages-base-layout";
    }


    @PostMapping("")
    public String addGaragesPost(HttpSession httpSession, @ModelAttribute GarageCreateModel garageCreateModel){

        this.garageService.create(garageCreateModel, (Long) httpSession.getAttribute("userId"));

        return "redirect:/garages";
    }
//
//    @GetMapping("{id}")
//    public String getGarageById(Model model, @PathVariable("id") long garageId){
//        GarageViewModel garageViewModel = this.garageService.getOneById(garageId);
//
//        model.addAttribute("garage", garageViewModel);
//        model.addAttribute("view", "/fragments/garages-view");
//        model.addAttribute("title", "Garages");
//
//        return "garages-base-layout";
//    }

    private long addUserIdToSession(HttpSession httpSession, User principal) {
        long userId = principal.getId();
        httpSession.setAttribute("userId", userId);
        if (httpSession.getAttribute("activeCar") != null) {
            httpSession.removeAttribute("activeCar");
        }
        return userId;
    }

}
