package com.car.areas.user.servicesImpl;

import com.car.areas.user.entities.BasicUser;
import com.car.areas.user.entities.Role;
import com.car.areas.user.models.bindingModels.RegistrationModel;
import com.car.areas.user.models.viewModels.UserViewModel;
import com.car.areas.user.repositories.BasicUserRepository;
import com.car.areas.user.services.RoleService;
import com.car.areas.user.services.BasicUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 03/04/2017
 */
@Service
public class BasicBasicUserServiceImpl implements BasicUserService {

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    // Causes circular dependency exception TODO: do some research
//    @Autowired
//    public BasicBasicUserServiceImpl(BasicUserRepository basicUserRepository, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper
//            modelMapper) {
//        this.basicUserRepository = basicUserRepository;
//        this.roleService = roleService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        this.modelMapper = modelMapper;
//    }

    @Override
    public void register(RegistrationModel registrationModel) {
        BasicUser user = modelMapper.map(registrationModel, BasicUser.class);
        String encodedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encodedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setCreationDate(new Date());
        Role role = this.roleService.getDefaultRole();
        user.addRole(role);
        this.basicUserRepository.save(user);
    }

    @Override
    public List<UserViewModel> getAll() {
        Iterable<BasicUser> users = this.basicUserRepository.findAll();
        List<UserViewModel> userViewModels = new ArrayList<>();
        for (BasicUser user : users) {
            userViewModels.add(modelMapper.map(user, UserViewModel.class));
        }

        return userViewModels;

    }

    @Override
    public BasicUser findByName(String userName) {
        return this.basicUserRepository.findOneByUsername(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BasicUser user = this.basicUserRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid credentials");
        }

        return user;
    }
}
