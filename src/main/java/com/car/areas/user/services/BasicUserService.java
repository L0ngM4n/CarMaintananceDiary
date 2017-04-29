package com.car.areas.user.services;

import com.car.areas.user.entities.BasicUser;
import com.car.areas.user.models.bindingModels.RegistrationModel;
import com.car.areas.user.models.viewModels.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 03/04/2017
 */
public interface BasicUserService extends UserDetailsService {

    void register(RegistrationModel registrationModel);

    List<UserViewModel> getAll();

    BasicUser findByName(String userName);
}
