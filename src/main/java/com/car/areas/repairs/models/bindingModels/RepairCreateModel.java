package com.car.areas.repairs.models.bindingModels;

import com.car.areas.repairs.entities.enums.RepairType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 20/04/2017
 */

public class RepairCreateModel {

    private long id;

    @NotEmpty
    @Length(min = 5, message = "Enter repair title")
    private String title;

    private String description;

    private String garage;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private double labourPrice;

    private RepairType repairType;

    private Set<PartCreateModel> parts;

    private long carId;

    public RepairCreateModel() {
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

    public Set<PartCreateModel> getParts() {
        return parts;
    }

    public void setParts(Set<PartCreateModel> parts) {
        this.parts = parts;
    }

    public long getCar() {
        return carId;
    }

    public void setCar(long carId) {
        this.carId = carId;
    }

    public RepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(RepairType repairType) {
        this.repairType = repairType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLabourPrice() {
        return labourPrice;
    }

    public void setLabourPrice(double labourPrice) {
        this.labourPrice = labourPrice;
    }
}
