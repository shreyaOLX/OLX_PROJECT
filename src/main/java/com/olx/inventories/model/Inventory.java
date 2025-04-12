package com.olx.inventories.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @Column
    private String attribute;
    @Column
    private String status;


}
