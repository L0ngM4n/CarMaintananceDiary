package com.car.areas.cars.servicesImpl;

import com.car.areas.cars.entities.Car;
import com.car.areas.cars.entities.CarModel;
import com.car.areas.cars.models.bindinngModels.CarCreateModel;
import com.car.areas.cars.models.viewModels.CarViewModel;
import com.car.areas.cars.repositories.CarModelsRepository;
import com.car.areas.cars.repositories.CarsRepository;
import com.car.areas.cars.services.CarService;
import com.car.areas.user.entities.BasicUser;
import com.car.areas.user.services.BasicUserService;
import com.car.exceptions.CarNotFoundException;
import com.car.exceptions.CarsNotFoundException;
import com.car.exceptions.ModelNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 21/04/2017
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarModelsRepository carModelsRepository;

    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private BasicUserService basicUserService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void create(CarCreateModel carCreateModel, String userName) {

        BasicUser user = this.basicUserService.findByName(userName);
        Car car = this.modelMapper.map(carCreateModel, Car.class);
        car.setUser(user);
        this.carsRepository.save(car);
    }

    @Override
    public CarViewModel getById(long id) {
        Car car = this.carsRepository.findOne(id);
        if (car == null) {
            throw new CarNotFoundException("Stop being smart and use the interface");
        }
        CarViewModel carViewModel = this.modelMapper.map(car, CarViewModel.class);
        return carViewModel;
    }

    @Override
    public List<CarViewModel> getAllCarModels() {
        Iterable<CarModel> cars = this.carModelsRepository.findAll();
        List<CarViewModel> carViewModels = new ArrayList<>();
        for (CarModel car : cars) {
            carViewModels.add(this.modelMapper.map(car, CarViewModel.class));
        }

        return carViewModels;
    }

    @Override
    public List<String> getAllCarMakers() {

        return this.carModelsRepository.getMakers();
    }


    @Override
    public List<String> getCarModels(String make) {
        List<String> models = this.carModelsRepository.getAllByMake(make);
        if (models == null) {
            throw new ModelNotFoundException();
        }

        return models;
    }

    @Override
    public List<String> getCarModelYears(String make, String carModel) {
        List<String> years = this.carModelsRepository.getYearsByMakeAndModel(make, carModel);
        if (years == null) {
            throw new ModelNotFoundException();
        }
        return years;
    }

    @Override
    public List<CarViewModel> getAllCarsByUser(long userId) {
        Set<Car> cars = this.carsRepository.getAllByUserId(userId);
        if (cars == null) {
            throw new CarsNotFoundException();
        }

        List<CarViewModel> carViewModels = new ArrayList<>();

        for (Car car : cars) {
            carViewModels.add(this.modelMapper.map(car, CarViewModel.class));
        }

        return carViewModels;
    }
}
