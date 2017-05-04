package com.car.areas.repairs.services;

import com.car.areas.repairs.models.bindingModels.PartCreateModel;

/**
 * 30/04/2017
 */
public interface PartService {

    void save(PartCreateModel partCreateModel, long id);

    void delete(long partId);
}
