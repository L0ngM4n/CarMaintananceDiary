package com.car.areas.cars.services;

import com.car.areas.cars.models.bindinngModels.CarCreateModel;
import com.car.areas.cars.models.viewModels.CarViewModel;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 21/04/2017
 */

public interface CarService {


    void create(CarCreateModel carCreateModel, String userName);

    CarViewModel getById(long id);

    List<CarViewModel> getAllCarModels();

    List<String> getAllCarMakers();

    List<String> getCarModels(String make);

    List<String> getCarModelYears(String make, String carModel);

    List<CarViewModel> getAllCarsByUser(long userId);

    void deleteCar(long carId, HttpSession httpSession);
}
