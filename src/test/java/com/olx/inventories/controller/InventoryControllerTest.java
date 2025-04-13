package com.olx.inventories.controller;

import com.olx.inventories.model.Inventory;
import com.olx.inventories.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
public class InventoryControllerTest {

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private InventoryController inventoryController;

    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
        inventory.setId(1L);
        inventory.setLocation("Delhi");
        inventory.setCreatedBy("admin");
        inventory.setType("car");
        inventory.setCostPrice(50000L);
        inventory.setSellingPrice(70000L);
        inventory.setAttribute("{\"color\":\"red\"}");
        inventory.setStatus("CREATED");
    }

    @Test
    public void testGetById() {
        when(inventoryService.get(1L)).thenReturn(ResponseEntity.ok("Inventory found"));

        ResponseEntity<String> response = inventoryController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Inventory found", response.getBody());
    }

    @Test
    public void testCreateInventory() {
        when(inventoryService.create(any())).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Created"));

        ResponseEntity<String> response = inventoryController.createInventory(inventory);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Created", response.getBody());
    }

    @Test
    public void testGetByPage() {
        Page<Inventory> page = new PageImpl<>(Collections.singletonList(inventory));

        when(inventoryService.getPage(any(PageRequest.class))).thenReturn(page);

        ResponseEntity<Page<Inventory>> response = inventoryController.getByPage(0);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertFalse(response.getBody().isEmpty());
    }

    @Test
    public void testUpdateInventory() {
        when(inventoryService.updateInventory(eq(1L), any())).thenReturn(ResponseEntity.ok("Updated"));

        ResponseEntity<String> response = inventoryController.updateInventory(1L, inventory);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated", response.getBody());
    }

    @Test
    public void testDeleteInventory() {
        when(inventoryService.delete(1L)).thenReturn(ResponseEntity.ok("Deleted"));

        ResponseEntity<String> response = inventoryController.deleteInventory(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted", response.getBody());
    }

}
