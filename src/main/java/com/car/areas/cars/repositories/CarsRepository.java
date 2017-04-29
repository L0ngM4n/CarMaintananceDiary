package com.car.areas.cars.repositories;


import com.car.areas.cars.entities.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * 21/04/2017
 */
@Repository
public interface CarsRepository extends CrudRepository<Car, Long> {

    Set<Car> getAllByUserId(long id);

}
