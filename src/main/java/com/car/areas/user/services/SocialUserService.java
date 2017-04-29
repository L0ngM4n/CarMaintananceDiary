package com.car.areas.user.services;

import org.springframework.social.facebook.api.User;

/**
 * 03/04/2017
 */
public interface SocialUserService {

    void registerOrLogin(User facebookUser);
}
