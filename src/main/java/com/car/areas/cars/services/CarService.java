package com.car.areas.cars.services;

import com.car.areas.cars.models.bindinngModels.CarCreateModel;
import com.car.areas.cars.models.viewModels.CarViewModel;

import java.util.Set;

/**
 * 21/04/2017
 */

public interface CarService {


    void create(CarCreateModel carCreateModel, String userName);

    CarViewModel getById(long id);

    Set<CarViewModel> getAllCarModels();

    Set<String> getAllCarMakers();

    Set<String> getCarModels(String make);

    Set<String> getCarModelYears(String make, String carModel);

    Set<CarViewModel> getAllCarsByUser(long userId);

}
