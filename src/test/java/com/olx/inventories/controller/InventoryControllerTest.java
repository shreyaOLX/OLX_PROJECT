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
        inventory.setVIN("VIN123");
        inventory.setLocation("Delhi");
        inventory.setCreatedBy("admin");
        inventory.setType("car");
        inventory.setCostPrice(50000L);
        inventory.setSellingPrice(70000L);
        inventory.setAttribute("{\"color\":\"red\"}");
        inventory.setStatus("CREATED");
    }


}
