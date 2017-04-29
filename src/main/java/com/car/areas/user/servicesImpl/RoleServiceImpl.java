package com.car.areas.user.servicesImpl;

import com.car.areas.user.entities.Role;
import com.car.areas.user.repositories.RoleRepository;
import com.car.areas.user.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    public static final String DEFAULT_ROLE = "ROLE_USER";

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getDefaultRole() {
        return this.roleRepository.findOneByAuthority(DEFAULT_ROLE);
    }
}
