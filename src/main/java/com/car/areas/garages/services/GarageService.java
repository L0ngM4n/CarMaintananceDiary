package com.car.areas.garages.services;

import com.car.areas.garages.models.GarageEditModel;
import com.car.areas.garages.models.bindinngModels.GarageCreateModel;
import com.car.areas.garages.models.viewModels.GarageViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 30/04/2017
 */
public interface GarageService {

    List<GarageViewModel> getAllByUserId(long userId);

    Page<GarageViewModel> getAllByUserId(Pageable pageable, long userId);

    void create(GarageCreateModel garage, long userId);

    GarageViewModel getOneById(long garageId);

    void delete(long id);

    void update(GarageEditModel garageModel);
}
