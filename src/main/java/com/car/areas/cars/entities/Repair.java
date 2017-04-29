package com.car.areas.cars.entities;

import javax.persistence.*;
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
}
