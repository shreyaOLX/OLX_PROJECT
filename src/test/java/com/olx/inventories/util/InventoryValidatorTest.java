package com.olx.inventories.util;

import com.olx.inventories.Enum.ItemType;
import com.olx.inventories.model.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class InventoryValidatorTest {
    private InventoryValidator inventoryValidator;
    @BeforeEach
    void setUp() {
        inventoryValidator = new InventoryValidator();
    }
    @Test
    void returnNullWhenInventoryIsValid(){
        Inventory inventory = new Inventory();
        inventory.setType(ItemType.CAR.name());
        inventory.setLocation("Delhi");
        inventory.setCostPrice(800000L);
        inventory.setSellingPrice(9000000L);
        inventory.setAttribute("""
                {
                "VIN" : "UP38XX0001",
                "make" : "Toyota",
                "model" : "Fortuner",
                "year" : "2023"
                }
                """);
        inventory.setStatus("CREATED");
        ResponseEntity<String> response = inventoryValidator.validate(inventory);
        assertNull(response);
    }
    @Test
    void returnErrorForInvalidType() {
        Inventory inventory = new Inventory();
        inventory.setType("InvalidType");
        inventory.setLocation("Prayagraj");
        inventory.setCostPrice(100000L);
        inventory.setSellingPrice(200000L);
        inventory.setAttribute("{\"VIN\":\"UP38HG0001\"}");
        inventory.setStatus("CREATED");
        ResponseEntity<String> response = inventoryValidator.validate(inventory);
        assertTrue(response.getBody().contains("not a valid type"));
        assertEquals(403, response.getStatusCodeValue());
    }
    @Test
    void returnErrorForInvalidAttributeJson() {
        Inventory inventory = new Inventory();
        inventory.setType(ItemType.CAR.name());
        inventory.setLocation("Delhi / South");
        inventory.setCostPrice(100000L);
        inventory.setSellingPrice(200000L);
        inventory.setAttribute("invalid-json");
        inventory.setStatus("CREATED");

        ResponseEntity<String> response = inventoryValidator.validate(inventory);
        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid JSON attribute", response.getBody());
    }


}