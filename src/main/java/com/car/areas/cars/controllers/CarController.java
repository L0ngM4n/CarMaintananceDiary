package com.car.areas.cars.controllers;

import com.car.areas.cars.models.bindinngModels.CarCreateModel;
import com.car.areas.cars.models.viewModels.CarViewModel;
import com.car.areas.cars.services.CarService;
import com.car.areas.user.entities.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * 21/04/2017
 */
@Controller
@RequestMapping("cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private Gson gson;

    @ModelAttribute("userCars")
    public Set<CarViewModel> userCars() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = ((User) principal).getId();
        Set<CarViewModel> carViewModels = this.carService.getAllCarsByUser(userId);
        return carViewModels;
    }

    @ResponseBody
    @GetMapping("user")
    public String getCars(HttpSession httpSession){
        long userId = (long) httpSession.getAttribute("userId");
        Set<CarViewModel> carViewModels = this.carService.getAllCarsByUser(userId);
        return this.gson.toJson(carViewModels);
    }

    @ResponseBody
    @GetMapping("brands")
    public String getBrands() {
        Set<String> brands = this.carService.getAllCarMakers();

        return this.gson.toJson(brands);
    }

    @ResponseBody
    @GetMapping("models")
    public String getModels(@RequestParam() String make, @RequestParam(name = "model", required = false) String
            carModel) {
        Set<String> carInfo;
        if (carModel == null) {
            carInfo = this.carService.getCarModels(make);
        } else {
            carInfo = this.carService.getCarModelYears(make, carModel);
        }

        return gson.toJson(carInfo);
    }

    @GetMapping("add")
    public String addCar(Model model) {

        model.addAttribute("title", "Add Car");
        model.addAttribute("view", "/fragments/add-car-form");
        return "base-layout";
    }

    @PostMapping("add")
    public String postAddCar(@ModelAttribute CarCreateModel carCreateModel) {
        String make = carCreateModel.getMake();
        System.out.println(make == null ? "Empty" : make);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        this.carService.create(carCreateModel, userName);
        //Add owner id to car entity

        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("active")
    public void setActiveCar(@RequestParam("carId") Long activeCarId, HttpSession httpSession) {

        httpSession.setAttribute("activeCar", activeCarId);
    }

    @GetMapping("{id}")
    public String getCarDetails(@PathVariable("id") long carId, Model model, HttpSession httpSession) {
        CarViewModel carViewModel = this.carService.getById(carId);
        httpSession.setAttribute("activeCar", carViewModel);
        model.addAttribute("car", carViewModel);
        model.addAttribute("view", "/fragments/car-repairs");

        return "base-layout";
    }
}
