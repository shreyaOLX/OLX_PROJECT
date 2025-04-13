package com.olx.inventories.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.inventories.model.Inventory;
import com.olx.inventories.repository.InventoryRepository;
import com.olx.inventories.util.InventoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImplementation implements InventoryService {

    private final InventoryRepository repository;

    private final InventoryValidator validator;

    private ObjectMapper objectMapper;

    @Autowired
    public InventoryServiceImplementation(InventoryRepository repository,
                                          ObjectMapper objectMapper,
                                          InventoryValidator validator) {
        this.repository = repository;
        this.validator = validator;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<String> create(Inventory item) {
        ResponseEntity<String> checkValidity = validator.validate(item);
        if (checkValidity != null) return checkValidity;

        item.setCreationDate(LocalDateTime.now().toString());
        item.setLastUpdatedDate();
        item.setStatus();

        try {
            repository.save(item);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        String response = "{id: " + item.getId() + ", status: 200}";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> getAll() {
        try {
            List<Inventory> inventory = repository.findAll();
            String jsonResponse = objectMapper.writeValueAsString(inventory);
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> get(Long id) {
        return null;
    }

    @Override
    public Page<Inventory> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateInventory(Long id, Inventory item) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateStatus(Long id, String status) {
        return null;
    }

    @Override
    public ResponseEntity<String> updatePricing(Long id, Long costPrice, Long sellingPrice) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateAttribute(Long id, String attribute) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        return null;
    }

    /// Helping Methods


}
