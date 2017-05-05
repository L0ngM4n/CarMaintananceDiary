package com.car.areas.cars.controllers;

import com.car.areas.cars.models.bindinngModels.CarCreateModel;
import com.car.areas.cars.models.viewModels.CarViewModel;
import com.car.areas.cars.services.CarService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

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


    @ResponseBody
    @GetMapping("user")
    public String getCars(HttpSession httpSession){
        long userId = (long) httpSession.getAttribute("userId");
        List<CarViewModel> carViewModels = this.carService.getAllCarsByUser(userId);
        return this.gson.toJson(carViewModels);
    }

    @ResponseBody
    @GetMapping("brands")
    public String getBrands() {
        List<String> brands = this.carService.getAllCarMakers();

        return this.gson.toJson(brands);
    }

    @ResponseBody
    @GetMapping("models")
    public String getModels(@RequestParam() String make, @RequestParam(name = "model", required = false) String
            carModel) {
        List<String> carInfo;
        if (carModel == null) {
            carInfo = this.carService.getCarModels(make);
        } else {
            carInfo = this.carService.getCarModelYears(make, carModel);
        }

        return gson.toJson(carInfo);
    }

    @GetMapping("add")
    public String addCar(Model model, @ModelAttribute CarCreateModel carCreateModel ) {

        model.addAttribute("title", "Add Car");
        model.addAttribute("view", "/fragments/add-car-form");
        return "base-layout";
    }

    //TODO Finish errors redirect to view
    @PostMapping("add")
    public String addCarPost(Model model, @Valid @ModelAttribute CarCreateModel carCreateModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if(bindingResult.hasErrors()){
            model.addAttribute("carCreateModel", carCreateModel);
            model.addAttribute("title", "New car");
            model.addAttribute("view", "/fragments/repair");
            return "base-layout";
        }

        String make = carCreateModel.getMake();
        System.out.println(make == null ? "Empty" : make);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        this.carService.create(carCreateModel, userName);
        //Add owner id to car entity

        return "redirect:/";
    }

    @GetMapping("all")
    public String allCarsPage(HttpSession httpSession, Model model) {
        long userId = (long) httpSession.getAttribute("userId");
        List<CarViewModel> carViewModels = this.carService.getAllCarsByUser(userId);
        model.addAttribute("cars", carViewModels);
        model.addAttribute("title", "User cars");
        model.addAttribute("view", "fragments/all-cars-list");


        return "base-layout";

    }

    @GetMapping("delete/{carId}")
    public String deleteCar(@PathVariable("carId") long carId, HttpSession httpSession) {

        this.carService.deleteCar(carId, httpSession);

        return "redirect:/cars/all";
    }




    @ResponseBody
    @PostMapping("active")
    public void setActiveCar(@RequestParam("carId") Long activeCarId, HttpSession httpSession) {
        CarViewModel carViewModel = this.carService.getById(activeCarId);
        httpSession.setAttribute("activeCar", carViewModel);
        httpSession.setAttribute("activeCarId", activeCarId);
    }

    @GetMapping("{id}/repairs")
    public String getCarById(@PathVariable("id") long carId, Model model, HttpSession httpSession) {
        CarViewModel carViewModel = this.carService.getById(carId);
        httpSession.setAttribute("activeCar", carViewModel);
        model.addAttribute("car", carViewModel);
        model.addAttribute("title", carViewModel.getModel());
        model.addAttribute("view", "/fragments/car-repairs-list");

        return "base-layout";
    }
}
