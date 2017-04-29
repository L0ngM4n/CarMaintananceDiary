package com.car.areas.user.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 12/04/2017
 */
@Entity
@DiscriminatorValue("BASIC_USER")
public class BasicUser extends User {

}
