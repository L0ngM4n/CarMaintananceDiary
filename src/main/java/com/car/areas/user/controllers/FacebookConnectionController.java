package com.car.areas.user.controllers;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;

/**
 * 13/04/2017
 */
@Controller
public class FacebookConnectionController extends ConnectController {

    public FacebookConnectionController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository
            connectionRepository) {
        super(connectionFactoryLocator, connectionRepository);
    }

    @Override
    protected String connectedView(String providerId) {
        return "redirect:/register/facebook";
    }
}

























