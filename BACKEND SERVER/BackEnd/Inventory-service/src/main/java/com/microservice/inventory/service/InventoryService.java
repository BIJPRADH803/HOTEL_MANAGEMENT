package com.microservice.inventory.service;

import java.util.List;

import com.microservice.inventory.entity.Inventory;
import com.microservie.inventory.exceptions.NotFoundException;

public interface InventoryService {
	
    Inventory createInventory(Inventory inventory);
    
    
    Inventory getInventoryById(String id) throws NotFoundException;
    
    
    Inventory updateInventory(String id, Inventory inventory) throws NotFoundException;
    
    
    void deleteInventory(String id) throws NotFoundException;
    
    List<Inventory> getAllInventory();

}
