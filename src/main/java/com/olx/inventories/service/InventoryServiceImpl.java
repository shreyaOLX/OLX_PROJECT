package com.olx.inventories.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.inventories.model.Inventory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class InventoryValidator {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseEntity<String> validate(Inventory item) {
        // Validate the item type.
        if (!validType(item.getType())) {
            String response = item.getType() + " is not a valid type of item";
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

        // Validate location: must contain only letters.
        if (!item.getLocation().matches("[a-zA-Z]+")) {
            return new ResponseEntity<>("Invalid Location", HttpStatus.BAD_REQUEST);
        }

        // Validate pricing.
        if (item.getCostPrice() <= 0 || item.getSellingPrice() <= 0) {
            return new ResponseEntity<>("Invalid Cost Price", HttpStatus.BAD_REQUEST);
        }

        // Validate attribute JSON.
        JsonNode jsonNode = null;
        if (item.getAttribute() != null) {
            try {
                jsonNode = objectMapper.readTree(item.getAttribute().toString());
            } catch (Exception e) {
                return new ResponseEntity<>("Invalid attribute JSON", HttpStatus.BAD_REQUEST);
            }
        }
        if (jsonNode == null) {
            return new ResponseEntity<>("Invalid Attribute", HttpStatus.BAD_REQUEST);
        }

        // Validate status.
        if (!"CREATED".equalsIgnoreCase(item.getStatus()) &&
                !"BOOKED".equalsIgnoreCase(item.getStatus()) &&
                !"SOLD".equalsIgnoreCase(item.getStatus())) {
            return new ResponseEntity<>("Invalid status. Status can only be CREATED, BOOKED or SOLD.", HttpStatus.BAD_REQUEST);
        }

        // All validations passed.
        return null;
    }

    // Example of a valid type check – adjust logic as needed.
    private boolean validType(String type) {
        return type != null && (type.equalsIgnoreCase("PHONE") || type.equalsIgnoreCase("FURNITURE"));
    }
}
