package com.microservice.staff.controller;

import java.util.List;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.staff.entity.Staff;
import com.microservice.staff.service.StaffService;
import com.microservie.staff.exceptions.NotFoundException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/staff")
public class StaffController {
	/*
	  author:Bijay Kuamr 
	 */

	Logger logger = LoggerFactory.getLogger(StaffController.class);
	
    @Autowired
    private StaffService staffService;

    @GetMapping()
    public ResponseEntity<List<Staff>> getAllStaff() {
    	logger.info("Getting list of Staff");
        List<Staff> staffList = staffService.getAllStaff();
        return new ResponseEntity<>(staffList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable String id) {
    	logger.info("Getting Staff by ID");
        Staff staff = staffService.getStaffById(id);
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Staff> addStaff(@Valid @RequestBody Staff staff) {
    	logger.info("Adding Staff");
        Staff savedStaff = staffService.addStaff(staff);
        return new ResponseEntity<>(savedStaff, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable String id, @Valid @RequestBody Staff staff) {
        try {
        	logger.info("Updating Staff");
            Staff updatedStaff = staffService.updateStaff(id, staff);
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable String id) {
    	logger.info("Deleting staff ");
        staffService.deleteStaff(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

