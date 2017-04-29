package com.car.areas.user.controllers;

import com.car.areas.user.services.SocialUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 13/04/2017
 * https://youtu.be/1YIw2nEPSMc?t=7476
 */
@Controller
@RequestMapping("/register")
public class FacebookLoginController {

    @Autowired
    private SocialUserService socialUserService;


    private Facebook facebook;


    private ConnectionRepository connectionRepository;

    public FacebookLoginController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping("/facebook")
    public String registerOrLogin() {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

//        User user = facebook.userOperations().getUserProfile();

        String userKey = this.connectionRepository
                .findPrimaryConnection(Facebook.class)
                .getKey()
                .getProviderUserId();
        String[] fields = {"id", "email"};
        User facebookUser = facebook.fetchObject(userKey, User.class, fields);

        this.socialUserService.registerOrLogin(facebookUser);
        return "redirect:/";
    }
}

























