package com.car.areas.cars.models.viewModels;

import com.car.areas.repairs.models.viewModels.RepairViewModel;

import java.util.HashSet;
import java.util.Set;

/**
 * 21/04/2017
 */
public class CarViewModel {

    private long id;

    private String make;

    private String model;

    private int year;

    private long userId;

    private Set<RepairViewModel> repairs;

    public CarViewModel() {
        this.setRepairs(new HashSet<>());
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Set<RepairViewModel> getRepairs() {
        return repairs;
    }

    public void setRepairs(Set<RepairViewModel> repairs) {
        this.repairs = repairs;
    }
}
