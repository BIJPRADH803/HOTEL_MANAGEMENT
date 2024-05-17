package com.microservice.staff.service;

import java.util.List;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.staff.entity.Staff;
import com.microservice.staff.repository.StaffRepository;
import com.microservie.staff.exceptions.NotFoundException;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;
    
    Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);
    @Override
    public List<Staff> getAllStaff() {
    	logger.info("Getting all staff list");
    	logger.info(" Successfully search of all staff");
        return staffRepository.findAll();
    }

    @Override
    public Staff getStaffById(String id) {
    	logger.info("Getting staff by Id");
    	logger.info(" Successfull search of staff by ID ");
        return staffRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Staff not found with id: " + id));
    }

    @Override
    public Staff addStaff(@Valid @NotNull Staff staff) {
    	logger.info("Adding Staff ");
    	logger.info(" Successfully added staff");
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(@NotNull String id, @Valid @NotNull Staff staff) {
    	logger.info("Updating Staff");
        Staff existingStaff = staffRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Staff not found with id: " + id));

        existingStaff.setName(staff.getName());
        existingStaff.setPosition(staff.getPosition());
        logger.info(" Successfully updated staff");
        return staffRepository.save(existingStaff);
    }

    @Override
    public void deleteStaff(String id) {
    	logger.info("Deleting Staff");
    	logger.info(" Successfully deleting staff");
        staffRepository.deleteById(id);
    }
}
