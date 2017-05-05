package com.car.areas.garages.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 04/05/2017
 */
public class GarageEditModel {

    private long id;

    @NotEmpty(message = "Field is required")
    @Length(min = 5, message = "Min length 5")
    private String name;

    @NotEmpty(message = "Fill field")
    private String address;

    @NotEmpty(message = "Fill field")
    private String longitude;

    @NotEmpty(message = "Fill field")
    private String latitude;

    private String description;

    public GarageEditModel() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
