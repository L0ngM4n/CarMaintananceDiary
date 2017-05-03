package com.car.areas.garages.servicesImpl;

import com.car.areas.garages.entities.Garage;
import com.car.areas.garages.models.bindinngModels.GarageCreateModel;
import com.car.areas.garages.models.viewModels.GarageViewModel;
import com.car.areas.garages.repositories.GarageRepository;
import com.car.areas.garages.services.GarageService;
import com.car.areas.user.entities.BasicUser;
import com.car.areas.user.repositories.BasicUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 30/04/2017
 */
@Service
public class GarageServiceImpl implements GarageService {

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<GarageViewModel> getAllByUserId(long userId) {
        List<GarageViewModel> garageViewModels = new ArrayList<>();
        List<Garage> garages = this.garageRepository.getAllByUserId(userId);

        for (Garage garage : garages) {
            garageViewModels.add(this.modelMapper.map(garage, GarageViewModel.class));
        }

        return garageViewModels;
    }

    @Override
    public void create(GarageCreateModel garageCreateModel, long userId) {

        Garage garage = this.modelMapper.map(garageCreateModel, Garage.class);
        BasicUser user = this.basicUserRepository.findOne(userId);
        garage.setUser(user);
//TODO if works
        garage = this.garageRepository.save(garage);

        user.addGarage(garage);
    }

    @Override
    public GarageViewModel getOneById(long garageId) {
        Garage garage = this.garageRepository.findOne(garageId);

        return this.modelMapper.map(garage, GarageViewModel.class);
    }
}
