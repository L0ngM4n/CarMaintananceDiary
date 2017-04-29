package com.car.areas.cars.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 19/04/2017
 */
@Entity
@Table(name = "cars_models")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String make;

    private String model;

    private int year;

    public CarModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
