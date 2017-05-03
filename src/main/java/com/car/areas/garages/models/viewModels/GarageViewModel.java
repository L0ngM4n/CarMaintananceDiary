package com.car.areas.garages.models.viewModels;

import com.car.areas.repairs.models.viewModels.RepairViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 30/04/2017
 */
public class GarageViewModel implements Serializable {

    private long id;

    private String name;

    private String address;

    private String longitude;

    private String latitude;

    private List<RepairViewModel> repairs;

    private long userId;

    public GarageViewModel() {
        this.repairs = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<RepairViewModel> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<RepairViewModel> repairs) {
        this.repairs = repairs;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
