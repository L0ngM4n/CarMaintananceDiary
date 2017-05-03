package com.car.areas.repairs.repositories;

import com.car.areas.repairs.entities.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 29/04/2017
 */
@Repository
public interface PartRepository extends CrudRepository<Part, Long> {

}
