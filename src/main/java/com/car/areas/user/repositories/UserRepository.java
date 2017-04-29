package com.car.areas.user.repositories;

import com.car.areas.user.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 03/04/2017
 */
@NoRepositoryBean
public interface UserRepository<T extends User> extends CrudRepository<T, Long> {

    T findOneByUsername(String username);
}
