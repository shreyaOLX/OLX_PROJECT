package com.olx.inventories.model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.inventories.Enum.ItemStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String VIN;
    @Column
    private String location;
    @Column
    private String createdBy;
    @Column
    private String type;
    @Column
    private Long costPrice;
    @Column
    private Long sellingPrice;
    @Column
    private String creationDate;
    @Column
    private String lastUpdateDate;
    @Setter
    @Column
    private String attribute;
    @Column
    private String status;

    public JsonNode getParsedAttribute() {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(this.attribute);
        }
        catch (Exception e){
            throw new RuntimeException("Unable to parse attribute JSON", e);
        }
    }
    public void setStatus() {
        this.status = ItemStatus.CREATED.toString();
    }

    public void setLastUpdatedDate() {
        this.lastUpdateDate = LocalDateTime.now().toString();
    }
}
