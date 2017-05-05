package com.car.areas.repairs.services;

import com.car.areas.repairs.models.bindingModels.PartCreateModel;
import com.car.areas.repairs.models.bindingModels.RepairCreateModel;
import com.car.areas.repairs.models.viewModels.RepairViewModel;

/**
 * 29/04/2017
 */
public interface RepairService {

    RepairViewModel create(RepairCreateModel repairCreateModel);

    void addPartToRepair(PartCreateModel partCreateModel, long repairId);

    RepairViewModel getById(long id);

    void updateRepairDetails(RepairCreateModel repairCreateModel);

    void deleteRepair(long repairId, long activeCarId);
}
