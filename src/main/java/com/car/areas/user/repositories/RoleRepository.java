package com.car.areas.user.repositories;

import com.car.areas.user.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findOneByAuthority(String authority);
}
