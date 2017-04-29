package com.car.areas.categories.models.bindingModels;

import org.hibernate.validator.constraints.Length;

/**
 * 20/04/2017
 */
public class CategoryCreateModel {

    @Length(min = 2, max = 20)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
