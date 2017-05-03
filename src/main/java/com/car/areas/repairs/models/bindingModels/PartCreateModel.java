package com.car.areas.repairs.models.bindingModels;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

/**
 * 20/04/2017
 */

public class PartCreateModel {

    @NotEmpty(message = "Supply part name")
    private String name;

    @Min(value = 1, message = "Price can not be negative")
    private double price;

    @NotEmpty(message = "Supplier Can not be empty")
    private String supplier;

    @NotEmpty
    private String brand;

    public PartCreateModel() {
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
