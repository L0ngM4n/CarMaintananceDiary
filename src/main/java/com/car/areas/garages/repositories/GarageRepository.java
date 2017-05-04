package com.car.areas.garages.repositories;

import com.car.areas.garages.entities.Garage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 30/04/2017
 */
@Repository
public interface GarageRepository extends PagingAndSortingRepository<Garage, Long> {

    List<Garage> getAllByUserId(long id);

    Page<Garage> getAllByUserId(Pageable pageable, long id);

    Garage findOneByName(String garage);
}
