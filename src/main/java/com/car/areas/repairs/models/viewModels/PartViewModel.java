package com.car.areas.repairs.models.viewModels;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

/**
 * 29/04/2017
 */
public class PartViewModel {

    private long id;

    @Length(min = 5)
    private String name;

    @Min(0)
    private double price;


    private String supplier;

    @NotEmpty
    @Length(min = 5)
    private String brand;

    private long repairID;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public long getRepairID() {
        return repairID;
    }

    public void setRepairID(long repairID) {
        this.repairID = repairID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
