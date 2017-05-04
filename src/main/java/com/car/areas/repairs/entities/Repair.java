package com.car.areas.repairs.entities;

import com.car.areas.cars.entities.Car;
import com.car.areas.garages.entities.Garage;
import com.car.areas.repairs.entities.enums.RepairType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 20/04/2017
 */
@Entity
@Table(name = "repairs")
public class Repair {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "garage_id", referencedColumnName = "id")
    private Garage garage;

    private Date date;

    private double labourPrice;

    @Enumerated(EnumType.STRING)
    private RepairType repairType;

    @OneToMany(mappedBy = "repair", targetEntity = Part.class)
    private Set<Part> parts;

    @ManyToOne
    private Car car;

    public Repair() {
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
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

    public void addPart(Part part) {
        this.parts.add(part);
    }
}
