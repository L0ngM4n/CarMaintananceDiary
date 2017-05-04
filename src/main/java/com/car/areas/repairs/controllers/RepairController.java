package com.car.areas.repairs.controllers;

import com.car.areas.cars.models.viewModels.CarViewModel;
import com.car.areas.cars.services.CarService;
import com.car.areas.garages.models.viewModels.GarageViewModel;
import com.car.areas.garages.services.GarageService;
import com.car.areas.repairs.models.bindingModels.PartCreateModel;
import com.car.areas.repairs.models.bindingModels.RepairCreateModel;
import com.car.areas.repairs.models.viewModels.RepairViewModel;
import com.car.areas.repairs.services.PartService;
import com.car.areas.repairs.services.RepairService;
import com.car.areas.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * 29/04/2017
 */
@Controller
@RequestMapping("repairs")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @Autowired
    private CarService carService;

    @Autowired
    private PartService partService;

    @Autowired
    GarageService garageService;

    @ModelAttribute("userCars")
    public Set<CarViewModel> userCars() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = ((User) principal).getId();
        Set<CarViewModel> carViewModels = this.carService.getAllCarsByUser(userId);
        return carViewModels;
    }

    @GetMapping("{id}")
    public String repairsPageGet(Model model, HttpSession  httpSession, @PathVariable long id, @ModelAttribute PartCreateModel partCreateModel) {

        RepairViewModel repairViewModel = this.repairService.getById(id);
        model.addAttribute("repair", repairViewModel);
        model.addAttribute("title", "New repair");
        model.addAttribute("view", "/fragments/repair");
        return "base-layout";
    }

    @PostMapping("{id}")
    public String addPart(Model model, @PathVariable long id, @Valid @ModelAttribute PartCreateModel partCreateModel, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            RepairViewModel repairViewModel = this.repairService.getById(id);
            model.addAttribute("repair", repairViewModel);
            model.addAttribute("title", "New repair");
            model.addAttribute("view", "/fragments/repair");
            return "base-layout";
        }

        this.partService.save(partCreateModel, id);


        return "redirect:/repairs/" + id;
    }

    @GetMapping("add")
    public String addRepairGet(HttpSession httpSession, Model model, @ModelAttribute RepairCreateModel repairCreateModel) {
        long userId = (long) httpSession.getAttribute("userId");
        List<GarageViewModel> garageViewModel = this.garageService.getAllByUserId(userId);

        model.addAttribute("garages", garageViewModel);
        model.addAttribute("view", "/fragments/add-car-repair");

        return "base-layout";
    }

    @PostMapping("add")
    public String addRepairPost(Model model, @ModelAttribute RepairCreateModel repairCreateModel, HttpSession httpSession, BindingResult bindingResult) {
        long activeCar = (long) httpSession.getAttribute("activeCar");
        repairCreateModel.setCar(activeCar);

        if (bindingResult.hasErrors()){
            model.addAttribute("title", "Add");
            model.addAttribute("view", "/fragments/add-car-repair");

            return "base-layout";
        }

        RepairViewModel repairViewModel = this.repairService.create(repairCreateModel);


        return "redirect:/repairs/" + repairViewModel.getId();//TODO add id to go to the repair and add parts
    }

    @PostMapping("add-part")
    public void addPart(@ModelAttribute PartCreateModel partCreateModel, @RequestParam("repairId") long repairId) {
        this.repairService.update(partCreateModel, repairId);
    }

}
