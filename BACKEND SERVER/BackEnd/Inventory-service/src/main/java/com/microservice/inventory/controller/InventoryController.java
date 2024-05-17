package com.microservice.inventory.controller;

import java.util.List;

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

import com.microservice.inventory.entity.Inventory;
import com.microservice.inventory.service.InventoryService;
import com.microservie.inventory.exceptions.NotFoundException;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	Logger logger = LoggerFactory.getLogger(InventoryController.class);
	
    @Autowired
    private InventoryService inventoryService;

    @PostMapping()
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
    	logger.info("Creating Inventory");
        Inventory createdInventory = inventoryService.createInventory(inventory);
        return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable String id) throws NotFoundException {
    	logger.info("Getting list of Inventory by id");
        Inventory inventory = inventoryService.getInventoryById(id);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Inventory>> getAllInventory() {
    	logger.info("Getting list of Inventory");
        List<Inventory> inventoryList = inventoryService.getAllInventory();
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable String id, @RequestBody Inventory inventory) throws NotFoundException {
    	logger.info("Updating Inventory");
        Inventory updatedInventory = inventoryService.updateInventory(id, inventory);
        return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable String id) throws NotFoundException {
    	logger.info("Deleting Inventory by id");
        inventoryService.deleteInventory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
