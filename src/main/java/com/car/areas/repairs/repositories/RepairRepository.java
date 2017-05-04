package com.car.areas.repairs.repositories;

import com.car.areas.repairs.entities.Repair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 29/04/2017
 */
@Repository
public interface RepairRepository extends CrudRepository<Repair, Long> {

}
