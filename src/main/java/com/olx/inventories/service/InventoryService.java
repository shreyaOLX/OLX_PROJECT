package com.olx.inventories.service;

import com.olx.inventories.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface InventoryService {

    ResponseEntity<String> create(Inventory item);

    ResponseEntity<String> getAll();

    ResponseEntity<String> get(Long id);

    Page<Inventory> getPage(Pageable pageable);

    ResponseEntity<String> updateInventory(Long id, Inventory item);

    ResponseEntity<String> updateStatus(Long id, String status);

    ResponseEntity<String> updatePricing(Long id, Long costPrice, Long sellingPrice);

    ResponseEntity<String> updateAttribute(Long id, String attribute);

    ResponseEntity<String> delete(Long id);
}
