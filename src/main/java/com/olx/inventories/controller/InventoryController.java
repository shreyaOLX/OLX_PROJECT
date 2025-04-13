package com.olx.inventories.controller;

import com.olx.inventories.model.Inventory;
import com.olx.inventories.service.InventoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Value("${inventory.items-per-page:10}")
    private int itemOnEachPage;

    @GetMapping("/items")
    public ResponseEntity<Page<Inventory>> getByPage(@RequestParam(required = false) int pageNum) {
        try {
            Pageable pageable = PageRequest.of(pageNum, itemOnEachPage);
            Page<Inventory> page = inventoryService.getPage(pageable);

            if (page.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(page);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/create")
    private ResponseEntity<String> createInventory(@RequestBody Inventory item){

        return inventoryService.create(item);

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateInventory(@PathVariable Long id, @RequestBody Inventory item) {
        return inventoryService.updateInventory(id, item);
    }


    @PatchMapping("/updateStatus/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return inventoryService.updateStatus(id, status);
    }

    @PatchMapping("/updatePricing/{id}")
    public ResponseEntity<String> updatePricing(@PathVariable Long id, @RequestParam(required = false) long costPrice, @RequestParam(required = false) long sellingPrice) {
        return inventoryService.updatePricing(id, costPrice, sellingPrice);
    }

    @PatchMapping("/updateAttribute/{id}")
    public ResponseEntity<String> updateAttribute(@PathVariable Long id, @RequestBody String attribute) {
        return inventoryService.updateAttribute(id, attribute);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long id){
        return inventoryService.delete(id);

    }

}