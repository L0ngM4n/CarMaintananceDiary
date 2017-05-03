package com.car.areas.repairs.servicesImpl;

import com.car.areas.cars.entities.Car;
import com.car.areas.cars.repositories.CarsRepository;
import com.car.areas.repairs.entities.Part;
import com.car.areas.repairs.entities.Repair;
import com.car.areas.repairs.models.bindingModels.PartCreateModel;
import com.car.areas.repairs.models.bindingModels.RepairCreateModel;
import com.car.areas.repairs.models.viewModels.RepairViewModel;
import com.car.areas.repairs.repositories.PartRepository;
import com.car.areas.repairs.repositories.RepairRepository;
import com.car.areas.repairs.services.RepairService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 29/04/2017
 */
@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RepairViewModel create(RepairCreateModel repairCreateModel) {
        Repair repair = this.modelMapper.map(repairCreateModel, Repair.class);

        Car car = this.carsRepository.findOne(repairCreateModel.getCar());
        repair.setCar(car);
        //TODO set Car id
        car.getRepairs().add(repair);
        repair = this.repairRepository.save(repair);
        return this.modelMapper.map(repair, RepairViewModel.class);
    }

    @Override
    public void update(PartCreateModel partCreateModel, long repairId) {
        Repair repair = this.repairRepository.findOne(repairId);

        Part part = this.modelMapper.map(partCreateModel, Part.class);
        part.setRepair(repair);
        this.partRepository.save(part);

    }

    @Override
    public RepairViewModel getById(long id) {
        Repair repair  = this.repairRepository.findOne(id);


        RepairViewModel repairViewModel = this.modelMapper.map(repair, RepairViewModel.class);
        return repairViewModel;
    }
}
