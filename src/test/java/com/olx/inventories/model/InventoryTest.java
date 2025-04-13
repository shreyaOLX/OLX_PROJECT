package com.olx.inventories.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void shouldParseVINFromAttribute() throws Exception{
        Inventory inventory = new Inventory();
        String attribute = """
                {
                "VIN" : "UP38HG0001",
                "make" : "Honda",
                "model" : "Civic",
                "year" : "2023"
                }
                """;
        inventory.setAttribute(attribute);
        JsonNode parsed = inventory.getParsedAttribute();
        assertEquals("UP38HG0001", parsed.get("VIN").asText());
        assertEquals("Honda", parsed.get("make").asText());
        assertEquals("Civic", parsed.get("model").asText());
        assertEquals("2023", parsed.get("year").asText());
    }
    @Test
    void setStatus() {
        Inventory inventory = new Inventory();
        inventory.setStatus();
        assertEquals(inventory.getStatus(), "CREATED");
    }
    @Test
    void setAndGetAllBasicField(){
        Inventory inventory = new Inventory();
        inventory.setLocation("Gurgaon");
        inventory.setCreatedBy("olx");
        inventory.setType("car");
        inventory.setCostPrice(100000L);
        inventory.setSellingPrice(150000L);

        assertEquals("Gurgaon", inventory.getLocation());
        assertEquals("olx", inventory.getCreatedBy());
        assertEquals("car", inventory.getType());
        assertEquals(100000L, inventory.getCostPrice());
        assertEquals(150000L, inventory.getSellingPrice());
    }

}