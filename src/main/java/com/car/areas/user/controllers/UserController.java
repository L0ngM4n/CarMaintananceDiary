package com.car.areas.user.controllers;

import com.car.areas.user.models.bindingModels.RegistrationModel;
import com.car.areas.user.models.viewModels.UserViewModel;
import com.car.areas.user.services.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * 03/04/2017
 */
@Controller
public class UserController {

    @Autowired
    private BasicUserService basicUserService;

    @GetMapping("/login")
    public String getLogin(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid Credentials");
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(){
        return "redirect:/";
    }


    @GetMapping("/register")
    public String getRegister(@ModelAttribute RegistrationModel registrationModel) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "register";

        }

        this.basicUserService.register(registrationModel);
        return "redirect:/login";
    }


    @GetMapping("/users")
    public String getUserPAge(Model model) {
        List<UserViewModel> users = basicUserService.getAll();

        model.addAttribute("users", users);
        return "users";
    }
}
