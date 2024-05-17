package com.microservice.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.microservice.inventory.entity.Inventory;
import com.microservice.inventory.repository.InventoryRepository;
import com.microservice.inventory.service.InventoryServiceImpl;
import com.microservie.inventory.exceptions.NotFoundException;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCreateInventory() {
        Inventory inventory = new Inventory("1", "Laptop", "10", 500);
        when(inventoryRepository.save(inventory)).thenReturn(inventory);

        Inventory result = inventoryService.createInventory(inventory);

        assertEquals("Laptop", result.getItemName());
        assertEquals("10", result.getQuantity());
        assertEquals(500, result.getPrice());
    }

    @Test
    void testGetInventoryById_Success() {
        Inventory inventory = new Inventory("1", "Laptop", "10", 500);
        when(inventoryRepository.findById("1")).thenReturn(Optional.of(inventory));

        Inventory result = inventoryService.getInventoryById("1");

        assertEquals("Laptop", result.getItemName());
        assertEquals("10", result.getQuantity());
        assertEquals(500, result.getPrice());
    }

    @Test
    void testGetInventoryById_NotFound() {
        when(inventoryRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> inventoryService.getInventoryById("1"));
    }

    @Test
    void testGetAllInventory() {
        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(new Inventory("1", "Laptop", "10", 500));
        when(inventoryRepository.findAll()).thenReturn(inventoryList);

        List<Inventory> result = inventoryService.getAllInventory();

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getItemName());
        assertEquals("10", result.get(0).getQuantity());
        assertEquals(500, result.get(0).getPrice());
    }
    @Test
    void testUpdateInventory_Success() {
        Inventory existingInventory = new Inventory("1", "Laptop", "10", 500);
        Inventory updatedInventory = new Inventory("1", "Desktop", "5", 600);
        when(inventoryRepository.existsById("1")).thenReturn(true);
        when(inventoryRepository.save(updatedInventory)).thenReturn(updatedInventory);

        Inventory result = inventoryService.updateInventory("1", updatedInventory);

        assertEquals("Desktop", result.getItemName());
        assertEquals("5", result.getQuantity());
        assertEquals(600, result.getPrice());
    }


    @Test
    void testUpdateInventory_NotFound() {
        when(inventoryRepository.existsById("1")).thenReturn(false);
        Inventory updatedInventory = new Inventory("1", "Desktop", "5", 600);

        assertThrows(NotFoundException.class, () -> inventoryService.updateInventory("1", updatedInventory));
    }

    @Test
    void testDeleteInventory_Success() {
        when(inventoryRepository.existsById("1")).thenReturn(true);
        doNothing().when(inventoryRepository).deleteById("1");

        assertDoesNotThrow(() -> inventoryService.deleteInventory("1"));
    }

    @Test
    void testDeleteInventory_NotFound() {
        when(inventoryRepository.existsById("1")).thenReturn(false);

        assertThrows(NotFoundException.class, () -> inventoryService.deleteInventory("1"));
    }
}
