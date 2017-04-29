package com.car.areas.categories.repositories;

import com.car.areas.categories.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 20/04/2017
 */

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {


}
