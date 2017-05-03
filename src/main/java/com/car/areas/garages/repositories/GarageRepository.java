package com.car.areas.garages.repositories;

import com.car.areas.garages.entities.Garage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 30/04/2017
 */
@Repository
public interface GarageRepository extends CrudRepository<Garage, Long> {

    List<Garage> getAllByUserId(long id);
}
