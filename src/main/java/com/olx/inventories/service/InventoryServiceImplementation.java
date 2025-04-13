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

    @Autowired
    private InventoryRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InventoryValidator validator;

    @Override
    public ResponseEntity<String> create(Inventory item) {
        // Validate first
        ResponseEntity<String> checkValidity = validator.validate(item);
        if (checkValidity != null) return checkValidity;

        // Set dates and status
        item.setCreationDate(setTodayDateTime());
        item.setLastUpdatedDate(setTodayDateTime());
        item.setStatus(); // or item.setStatus("CREATED") if that's your default

        try {
            repository.save(item);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        String response = "{id: " + item.getId() + ", status: 200}";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
