package com.olx.inventories.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.inventories.Enum.ItemType;
import com.olx.inventories.model.Inventory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class InventoryValidator {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseEntity<String> validate(Inventory item) {

        ///Basic validation checks
        if (!validType(item.getType())) {
            return new ResponseEntity<>(item.getType() + " is not a valid type of item", HttpStatus.FORBIDDEN);
        }

        if (!item.getLocation().matches("[a-zA-Z, /]+")) {
            return new ResponseEntity<>("Invalid Location", HttpStatus.BAD_REQUEST);
        }


        if (item.getCostPrice() <= 0 || item.getSellingPrice() <= 0) {
            return new ResponseEntity<>("Invalid Cost Price", HttpStatus.BAD_REQUEST);
        }

        /// Validate attribute JSON
        JsonNode jsonNode = null;
        if (item.getAttribute() != null) {
            try {
                jsonNode = objectMapper.readTree(item.getAttribute());
            } catch (Exception e) {
                return new ResponseEntity<>("Invalid attribute JSON", HttpStatus.BAD_REQUEST);
            }
        }
        if (jsonNode == null) {
            return new ResponseEntity<>("Invalid Attribute", HttpStatus.BAD_REQUEST);
        }

        String status = item.getStatus();
        if (!"CREATED".equalsIgnoreCase(status) && !"PROCURED".equalsIgnoreCase(status) && !"SOLD".equalsIgnoreCase(status)) {
            return new ResponseEntity<>("Invalid status. Status can only be PROCURED or SOLD.", HttpStatus.BAD_REQUEST);
        }

        return null;
    }

    private boolean validType(String type) {
        if (type == null || type.isEmpty()) {
            return false;
        }
        for (ItemType itemType : ItemType.values()) {
            if (type.equalsIgnoreCase(itemType.name())) {
                return true;
            }
        }
        return false;
    }
}