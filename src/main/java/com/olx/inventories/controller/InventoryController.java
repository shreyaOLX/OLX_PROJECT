package com.olx.inventories.controller;

import com.olx.inventories.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;


    @GetMapping("/")
    public ResponseEntity<String> getAll() {
        return inventoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable Long id) {
        return inventoryService.get(id);
    }
}