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

}