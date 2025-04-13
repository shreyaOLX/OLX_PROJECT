package com.olx.inventories.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.inventories.model.Inventory;
import com.olx.inventories.repository.InventoryRepository;
import com.olx.inventories.util.InventoryValidator;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {
    private InventoryRepository repository;
    private InventoryValidator validator;
    private ObjectMapper objectMapper;
    private InventoryServiceImplementation service;

    @BeforeEach
    void setUp() {
        repository = mock(InventoryRepository.class);
        validator = mock(InventoryValidator.class);
        objectMapper = new ObjectMapper();
        service = new InventoryServiceImplementation(repository,objectMapper,validator);
    }

    @Test
    void createAndCheckInInventory() {
        Inventory inventory = new Inventory();

    }

    @Test
    void getAll() {
    }

    @Test
    void get() {
    }

    @Test
    void getPage() {
    }

    @Test
    void updateInventory() {
    }

    @Test
    void updateStatus() {
    }

    @Test
    void updatePricing() {
    }

    @Test
    void updateAttribute() {
    }

    @Test
    void delete() {
    }
}