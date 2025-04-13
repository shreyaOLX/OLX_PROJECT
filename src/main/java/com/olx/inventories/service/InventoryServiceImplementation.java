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

    private final ObjectMapper objectMapper;

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
        /// Optional is safer , handles null Exceptions
        Optional<Inventory> item = repository.findById(id);
        try {
            if (item.isPresent()) {
                String jsonResponse = objectMapper.writeValueAsString(item.get());
                return ResponseEntity.ok(jsonResponse);
            }
            return ResponseEntity.status(404).body("Inventory not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @Override
    public Page<Inventory> getPage(Pageable pageable) {
        try {
            return repository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch inventory", e);
        }
    }

    @Override
    public ResponseEntity<String> updateInventory(Long id, Inventory item) {
        if (id == null || id <= 0) {
            return new ResponseEntity<>("Invalid ID. ID must be greater than 0.", HttpStatus.BAD_REQUEST);
        }

        Optional<Inventory> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            Inventory existingItem = existingItemOptional.get();

            ResponseEntity<String> response = validator.validate(item);
            if (response != null) {
                return response;
            }

            existingItem.setType(item.getType());
            existingItem.setLocation(item.getLocation());
            existingItem.setCostPrice(item.getCostPrice());
            existingItem.setSellingPrice(item.getSellingPrice());
            existingItem.setLastUpdatedDate();
            existingItem.setAttribute(item.getAttribute());
            existingItem.setStatus();

            repository.save(existingItem);
            return new ResponseEntity<>("Item updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> updateStatus(Long id, String status) {
        if (!"PROCURED".equalsIgnoreCase(status) && !"SOLD".equalsIgnoreCase(status)) {
            return new ResponseEntity<>("Invalid status. Status can only be PROCURED or SOLD.", HttpStatus.BAD_REQUEST);
        }

        Optional<Inventory> existingItemOptional = repository.findById(id);
        if (existingItemOptional.isEmpty()) {
            return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
        }

        Inventory existingItem = existingItemOptional.get();
        existingItem.setStatus(status);
        existingItem.setLastUpdatedDate();

        try {
            repository.save(existingItem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return new ResponseEntity<>("Status updated", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updatePricing(Long id, Long costPrice, Long sellingPrice) {
        if ((costPrice != null && costPrice <= 1) || (sellingPrice != null && sellingPrice <= 1)) {
            return new ResponseEntity<>("costPrice and sellingPrice must be greater than 0", HttpStatus.BAD_REQUEST);
        }

        Optional<Inventory> existingItemOptional = repository.findById(id);
        if (existingItemOptional.isEmpty()) {
            return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
        }

        Inventory existingItem = existingItemOptional.get();
        if (costPrice != null) existingItem.setCostPrice(costPrice);
        if (sellingPrice != null) existingItem.setSellingPrice(sellingPrice);
        existingItem.setLastUpdatedDate();

        repository.save(existingItem);
        return new ResponseEntity<>("Pricing updated", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateAttribute(Long id, String attribute) {
        Optional<Inventory> existingItemOptional = repository.findById(id);
        if (existingItemOptional.isPresent()) {
            Inventory existingItem = existingItemOptional.get();
            try {
                JsonNode jsonNode = objectMapper.readTree(attribute);
                existingItem.setAttribute(String.valueOf(jsonNode));
                existingItem.setLastUpdatedDate();
                repository.save(existingItem);
                return new ResponseEntity<>("Attribute updated", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Invalid attribute JSON", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Inventory> itemOptional = repository.findById(id);
        if (itemOptional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok("Item deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
