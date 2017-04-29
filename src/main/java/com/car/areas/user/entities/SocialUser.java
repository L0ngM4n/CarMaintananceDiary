package com.car.areas.user.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 12/04/2017
 */
@Entity
@DiscriminatorValue("SOCIAL_USER")
public class SocialUser extends User {

    private String provider;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
