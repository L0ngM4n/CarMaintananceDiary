package com.car.areas.repairs.models.viewModels;

import com.car.areas.repairs.entities.enums.RepairType;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 20/04/2017
 */

public class RepairViewModel {

    private long id;

    private String title;

    private String description;

    private String garage;

    private Date date;

    private double labourPrice;

    private RepairType repairType;

    private Set<PartViewModel> parts;

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

    public Set<PartViewModel> getParts() {
        return parts;
    }

    public void setParts(Set<PartViewModel> parts) {
        this.parts = parts;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getLabourPrice() {
        return labourPrice;
    }

    public void setLabourPrice(double labourPrice) {
        this.labourPrice = labourPrice;
    }

    public RepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(RepairType repairType) {
        this.repairType = repairType;
    }

    public double getPartsPrice() {
        return this.getParts().stream().map(PartViewModel::getPrice).reduce(0d, (a, b) -> a+ b);
    }
}
