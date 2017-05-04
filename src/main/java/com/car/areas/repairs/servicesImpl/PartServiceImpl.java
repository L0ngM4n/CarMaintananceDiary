package com.car.areas.repairs.servicesImpl;

import com.car.areas.repairs.entities.Part;
import com.car.areas.repairs.entities.Repair;
import com.car.areas.repairs.models.bindingModels.PartCreateModel;
import com.car.areas.repairs.repositories.PartRepository;
import com.car.areas.repairs.repositories.RepairRepository;
import com.car.areas.repairs.services.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 30/04/2017
 */
@Service
public class PartServiceImpl implements PartService {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(PartCreateModel partCreateModel, long id) {
        Part part = this.modelMapper.map(partCreateModel, Part.class);
        Repair repair = this.repairRepository.findOne(id);
        part.setRepair(repair);

        part = this.partRepository.save(part);
        repair.getParts().add(part);

    }
}
