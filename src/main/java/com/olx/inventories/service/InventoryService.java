package com.olx.inventories.service;

import com.olx.inventories.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class InventoryService {
    public ResponseEntity<String> create(Inventory item) {
        return null;
    }

    public ResponseEntity<String> updateInventory(Long id, Inventory item) {
        return null;
    }

    public ResponseEntity<String> updateStatus(Long id, String status) {
        return null;
    }

    public ResponseEntity<String> updatePricing(Long id, Long costPrice, Long sellingPrice) {
        return null;
    }

    public ResponseEntity<String> updateAttribute(Long id, String attribute) {
        return null;
    }


    public ResponseEntity<String> getAll() {
        return null;
    }

    public ResponseEntity<String> get(Long id) {
        return null;
    }

    public Page<Inventory> getPage(Pageable pageable) {
        return null;
    }

    public ResponseEntity<String> delete(Long id) {
        return null;
    }
}
