package com.car.areas.garages.services;

import com.car.areas.garages.models.bindinngModels.GarageCreateModel;
import com.car.areas.garages.models.viewModels.GarageViewModel;

import java.util.List;

/**
 * 30/04/2017
 */
public interface GarageService {

    List<GarageViewModel> getAllByUserId(long userId);

    void create(GarageCreateModel garage, long userId);

    GarageViewModel getOneById(long garageId);
}
