package com.olx.inventories.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.inventories.model.Inventory;
import com.olx.inventories.repository.InventoryRepository;
import com.olx.inventories.util.InventoryValidator;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        service = new InventoryServiceImplementation(repository, objectMapper, validator);
    }


    @Test
    void createAndCheckInInventory() {
        Inventory inventory = new Inventory();
        inventory.setType("car");
        inventory.setLocation("Delhi");
        inventory.setCostPrice(100000L);
        inventory.setSellingPrice(150000L);
        inventory.setAttribute("{\"color\":\"red\"}");
        inventory.setStatus("CREATED");
        ResponseEntity<String> response = service.create(inventory);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void getAll() {
        List<Inventory> inventories = new ArrayList<>();
        Inventory inventory1 = new Inventory();
        inventory1.setId(1L);
        inventory1.setType("car");
        Inventory inventory2 = new Inventory();
        inventory2.setId(2L);
        inventory2.setType("bike");
        inventories.add(inventory1);
        inventories.add(inventory2);

        when(repository.findAll()).thenReturn(inventories);
        ResponseEntity<String> response = service.getAll();

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        verify(repository).findAll();

    }

    @Test
    void get() {
        Long id = 1L;
        Inventory inventory = new Inventory();
        inventory.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(inventory));

        ResponseEntity<String> response = service.get(id);
        assertEquals(200, response.getStatusCode().value());

    }

    @Test
    void getPage() {
        List<Inventory> inventories = new ArrayList<>();
        inventories.add(new Inventory());
        Page<Inventory> page = new PageImpl<>(inventories);
        Pageable pageable = mock(Pageable.class);

        when(repository.findAll(pageable)).thenReturn(page);

        Page<Inventory> result = service.getPage(pageable);
        assertEquals(1, result.getContent().size());

    }

    @Test
    void delete() {
        Long id = 1L;
        Inventory inventory = new Inventory();
        inventory.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(inventory));

        ResponseEntity<String> response = service.delete(id);
        assertEquals(200, response.getStatusCode().value());

    }
}