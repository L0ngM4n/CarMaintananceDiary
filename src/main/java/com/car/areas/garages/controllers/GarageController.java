package com.car.areas.garages.controllers;

import com.car.areas.cars.services.CarService;
import com.car.areas.garages.models.GarageEditModel;
import com.car.areas.garages.models.bindinngModels.GarageCreateModel;
import com.car.areas.garages.models.viewModels.GarageViewModel;
import com.car.areas.garages.services.GarageService;
import com.car.areas.user.entities.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("garages")
public class GarageController {

    @Autowired
    private Gson gson;

    @Autowired
    private GarageService garageService;

    @Autowired
    private CarService carService;



    @GetMapping("")
    public String garage(Model model, HttpSession httpSession, @ModelAttribute GarageCreateModel garageCreateModel,@PageableDefault(size = 3) Pageable pageable) {
        addUserIdToSession(httpSession, (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        List<GarageViewModel> garages = this.garageService.getAllByUserId((Long) httpSession.getAttribute("userId"));
        Page<GarageViewModel> garages = this.garageService.getAllByUserId(pageable, (Long) httpSession.getAttribute("userId"));

        model.addAttribute("title", "Garages List");
        model.addAttribute("view", "/fragments/garages-list");
        model.addAttribute("garages", garages);

        return "garages-base-layout";
    }

    @GetMapping("add")
    public String garageAdd(Model model, HttpSession httpSession, @ModelAttribute GarageCreateModel garageCreateModel) {
        addUserIdToSession(httpSession, (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<GarageViewModel> garages = this.garageService.getAllByUserId((Long) httpSession.getAttribute("userId"));

        model.addAttribute("title", "Garages Add");
        model.addAttribute("view", "/fragments/garages-add");
        model.addAttribute("garages", garages);

        return "garages-base-layout";
    }


    @PostMapping("add")
    public String addGaragesPost(HttpSession httpSession, @Valid @ModelAttribute GarageCreateModel garageCreateModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("garageCreateModel", garageCreateModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.garageCreateModel", bindingResult);
            return "redirect:/garages/add";
        }


        this.garageService.create(garageCreateModel, (Long) httpSession.getAttribute("userId"));

        return "redirect:/garages";
    }

    @GetMapping("{id}")
    public String getGarageById(Model model, @PathVariable("id") long garageId){
        GarageViewModel garageViewModel = this.garageService.getOneById(garageId);

        model.addAttribute("garage", garageViewModel);
        model.addAttribute("view", "/fragments/garages-view");
        model.addAttribute("title", "Garages");

        return "garages-base-layout";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") long id){
        this.garageService.delete(id);
        return "redirect:/garages";
    }
//=================================================================================================
    @GetMapping("edit/{id}")
    public String getEdit(@PathVariable("id") long id, @ModelAttribute GarageEditModel garageEditModel, Model model){

        garageEditModel = this.garageService.getOneByIdForEdit(id);
        model.addAttribute("garageEditModel", garageEditModel);
        model.addAttribute("view", "/fragments/garage-edit");
        model.addAttribute("title", "Edit");
        return "garages-base-layout";
    }
//==============================Errors are lost after redirect and are 0 when back up
    @PostMapping("edit/{id}")
    public String postEdit(@PathVariable("id") long id, @Valid @ModelAttribute GarageEditModel garageEditModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        garageEditModel.setId(id);

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("garageEditModel", garageEditModel);
           redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.garageEditModel", bindingResult);
            return "redirect:/garages/edit/" + id;
        }


        this.garageService.update(garageEditModel);


        return "redirect:/garages";
    }
//
//===========================================================================================










    private long addUserIdToSession(HttpSession httpSession, User principal) {
        long userId = principal.getId();
        httpSession.setAttribute("userId", userId);
        if (httpSession.getAttribute("activeCar") != null) {
            httpSession.removeAttribute("activeCar");
        }
        return userId;
    }

}
