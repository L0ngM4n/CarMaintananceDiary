package com.car.areas.cars.repositories;


import com.car.areas.cars.entities.CarModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 21/04/2017
 */
@Repository
public interface CarModelsRepository extends CrudRepository<CarModel, Long> {

    @Query(value = "SELECT DISTINCT(c.make) FROM CarModel AS c ORDER BY c.make ASC ")
    List<String> getMakers();

    @Query(value = "SELECT DISTINCT(c.model) FROM CarModel AS c WHERE c.make = :make ORDER BY c.model ASC ")
    List<String> getAllByMake(@Param("make") String make);

    @Query(value = "SELECT DISTINCT(c.year) FROM CarModel AS c WHERE c.make = :make AND c.model = :model")
    List<String> getYearsByMakeAndModel(@Param("make") String make, @Param("model") String model);


}
