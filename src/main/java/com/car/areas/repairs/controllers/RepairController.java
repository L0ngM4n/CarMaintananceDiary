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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

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
    private GarageService garageService;

    @ModelAttribute("userCars")
    public List<CarViewModel> userCars() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = ((User) principal).getId();
        List<CarViewModel> carViewModels = this.carService.getAllCarsByUser(userId);
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
    public String addRepairPost(Model model, @Valid @ModelAttribute RepairCreateModel repairCreateModel, HttpSession httpSession, BindingResult bindingResult) {
        long activeCar = (long) httpSession.getAttribute("activeCarId");
        repairCreateModel.setCar(activeCar);

        if (bindingResult.hasErrors()){
            model.addAttribute("title", "Add");
            model.addAttribute("view", "/fragments/add-car-repair");

            return "base-layout";
        }

        RepairViewModel repairViewModel = this.repairService.create(repairCreateModel);


        return "redirect:/repairs/" + repairViewModel.getId();//TODO add id to go to the repair and add parts
    }


    @GetMapping("edit/{id}")
    public String editPage(Model model,@PathVariable("id") long repairId, @ModelAttribute RepairViewModel repairViewModel, HttpSession httpSession) {

        repairViewModel = this.repairService.getById(repairId);
        long userId = (long) httpSession.getAttribute("userId");
        List<GarageViewModel> garages = this.garageService.getAllByUserId(userId);

        model.addAttribute("title", "Edit");
        model.addAttribute("view", "fragments/repair-edit");
        model.addAttribute("repair", repairViewModel);
        model.addAttribute("parts", repairViewModel.getParts());
        model.addAttribute("garages", garages);


        return "base-layout";
    }


    @PostMapping("edit/{id}")
    public String editPagePost(Model model, @PathVariable("id") long repairId, @Valid @ModelAttribute RepairCreateModel repairCreateModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Edit");
            model.addAttribute("view", "fragments/repair-edit");
            model.addAttribute("repair", repairCreateModel);
            return "base-layout";
        }

        this.repairService.updateRepairDetails(repairCreateModel);



        return "redirect:/repairs/" + repairId;
    }

    @GetMapping("delete/{id}")
    public String repairDelete(HttpSession httpSession, @PathVariable("id") long repairId) {
        if (httpSession.getAttribute("activeCar") == null) {
            return "redirect:/";
        }
        long activeCarId = (long) httpSession.getAttribute("activeCarId");


        this.repairService.deleteRepair(repairId, activeCarId);

        return "redirect:/cars/"+ activeCarId + "/repairs";
    }


    @PostMapping("add-part")
    public void addPart(@ModelAttribute PartCreateModel partCreateModel, @RequestParam("repairId") long repairId) {
        this.repairService.addPartToRepair(partCreateModel, repairId);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("part/delete")
    public void deletePart (@RequestParam("partId") long partId){

        this.partService.delete(partId);
        
    }

}
