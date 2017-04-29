package com.car.areas.cars.models.viewModels;

import com.car.areas.cars.entities.Part;

import java.util.HashSet;
import java.util.Set;

/**
 * 20/04/2017
 */

public class RepairViewModel {

    private long id;

    private String title;

    private Set<Part> parts;

    private long carId;

    public RepairViewModel() {
        this.parts = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }
}
