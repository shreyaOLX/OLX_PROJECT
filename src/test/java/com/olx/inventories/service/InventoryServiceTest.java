package com.olx.inventories.service;

import com.olx.inventories.Enum.ItemType;
import com.olx.inventories.model.Inventory;
import com.olx.inventories.util.InventoryValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class InventoryValidatorTest {
    private InventoryValidator inventoryValidator;

    @BeforeEach
    void setUp() {
        inventoryValidator = new InventoryValidator();
    }

    @Test
    void returnNullWhenInventoryIsValid() {
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

        assertNotNull(response);
        assertTrue(Objects.requireNonNull(response.getBody()).contains("not a valid type"));
        assertEquals(403, response.getStatusCode().value());
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
        assertEquals(400, response.getStatusCode().value());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("Invalid attribute JSON"));
    }

    @Test
    void returnErrorForInvalidStatus() {
        Inventory inventory = new Inventory();
        inventory.setType(ItemType.CAR.name());
        inventory.setLocation("Delhi / North");
        inventory.setCostPrice(100000L);
        inventory.setSellingPrice(200000L);
        inventory.setAttribute("{\"VIN\":\"UP38HG0001\"}");
        inventory.setStatus("DELIVERED");

        ResponseEntity<String> response = inventoryValidator.validate(inventory);

        assertNotNull(response);
        assertEquals(400, response.getStatusCode().value());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("Invalid status"));
    }

    @Test
    void returnErrorForNegativePrices() {
        Inventory inventory = new Inventory();
        inventory.setType(ItemType.CAR.name());
        inventory.setLocation("Delhi");
        inventory.setCostPrice(-10000L);
        inventory.setSellingPrice(-20000L);
        inventory.setAttribute("{\"VIN\":\"UP38HG0001\"}");
        inventory.setStatus("CREATED");

        ResponseEntity<String> response = inventoryValidator.validate(inventory);

        assertNotNull(response);
        assertEquals(400, response.getStatusCode().value());
        assertFalse(Objects.requireNonNull(response.getBody()).contains("Prices cannot be negative"));
    }
}