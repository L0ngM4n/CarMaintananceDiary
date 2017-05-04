package com.car.areas.garages.models.bindinngModels;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 30/04/2017
 */
public class GarageCreateModel implements Serializable{

    @NotEmpty
    @Length(min = 1)
    private String name;

    @NotEmpty
    private String address;

    @NotEmpty
    private String longitude;

    @NotEmpty
    private String latitude;

    public GarageCreateModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}
