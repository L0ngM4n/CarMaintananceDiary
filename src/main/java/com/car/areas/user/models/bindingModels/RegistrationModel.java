package com.car.areas.user.models.bindingModels;


import com.car.areas.user.customValidations.ArePasswordsMatching;

import javax.validation.constraints.Size;

/**
 * 03/04/2017
 */
@ArePasswordsMatching
public class RegistrationModel {

    @Size(min = 5)
    private String username;

    @Size(min = 5)
    private String password;

    private String confirmPassword;

    public RegistrationModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
