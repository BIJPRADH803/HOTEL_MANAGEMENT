package com.microservice.inventory.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.inventory.entity.Inventory;
import com.microservice.inventory.repository.InventoryRepository;
import com.microservie.inventory.exceptions.NotFoundException;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
 
    Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);
    
    @Override
    public Inventory createInventory(Inventory inventory) {
    	logger.info("Creating Inventory ");
    	logger.info(" Successfull created of inventory");
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory getInventoryById(String id) throws NotFoundException {
    	logger.info("Getting Inventory by id");
    	logger.info(" Successfull search for Inventory by ID");
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory not found"));
    }
    
    
    @Override
    public List<Inventory> getAllInventory() {
    	logger.info("Getting Inventory list");
    	logger.info(" Successfull search of all inventory");
        return inventoryRepository.findAll();
    }


    @Override
    public Inventory updateInventory(String id, Inventory inventory) throws NotFoundException {
    	logger.info("Updating Inventory");
    	
        if (!inventoryRepository.existsById(id)) {
            throw new NotFoundException("Inventory not found");
        }
        inventory.setId(id);
        logger.info(" Inventory Updated Successfully");
        return inventoryRepository.save(inventory);
    }

    @Override
    public void deleteInventory(String id) throws NotFoundException {
    	logger.info("Deleting Inventory");
        if (!inventoryRepository.existsById(id)) {
            throw new NotFoundException("Inventory not found");
        }
        inventoryRepository.deleteById(id);
        logger.info(" Inventory deleted Successfully");
    }
}
