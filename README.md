# Inventory Management System

A robust inventory management system built with Spring Boot that allows tracking and management of inventory items with different types and statuses.

## Project Overview

This application provides a comprehensive solution for inventory management with features including:
- Item tracking with various status options (CREATED, PROCURED, SOLD)
- Item categorization by type (CAR, BIKE, MOBILE, LAPTOP, SHOES)
- RESTful API endpoints for inventory operations
- Data validation and error handling

## Project Structure

```
com.olx.inventories/
├── controller
│   └── InventoryController.java
├── enum
│   ├── Constant.java
│   ├── ItemStatus.java
│   └── ItemType.java
├── model
│   └── Inventory.java
├── repository
│   └── InventoryRepository.java
├── service
│   ├── InventoryService.java
│   └── InventoryServiceImplementation.java
├── util
│   └── InventoryValidator.java
└── InventoriesApplication.java
```

## Technology Stack

- **Java**: Core programming language
- **Spring Boot**: Application framework
- **Spring Data JPA**: Data access and persistence
- **Spring MVC**: Web layer and REST controller
- **Gradle**: Dependency management
- **JUnit & Mockito**: Testing frameworks

## Future Development Plans

### 1. User Module
- User registration and profile management
- Role-based access control (Admin, Manager, Staff)
- User activity logging and audit trails

### 2. Security Implementation
- JWT authentication and authorization
- Secure API endpoints
- Password encryption and security best practices

### 3. Entity Relationships
- Supplier management and relationships
- Warehouse/location entities
- Order management integration
- Transaction history

## Clean Code Practices

This project adheres to the following clean code principles:

1. **Meaningful Names**: Clear, intention-revealing names for classes, methods, and variables
2. **Single Responsibility Principle**: Each class has one responsibility
3. **DRY (Don't Repeat Yourself)**: Code reuse through proper abstraction
4. **SOLID Principles**: Following object-oriented design principles
5. **Comprehensive Testing**: Unit tests for all business logic
6. **Consistent Formatting**: Adherence to Java code style guidelines
7. **Error Handling**: Proper exception handling and validation

## API Documentation

The Inventory Management System provides a RESTful API for inventory operations:

### Inventory API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/inventories/` | Retrieve all inventory items |
| GET    | `/inventories/{id}` | Retrieve a specific inventory item by ID |
| GET    | `/inventories/items` | Get inventory items with pagination (query parameters: `pageNum`, defaults to 0) |
| POST   | `/inventories/create` | Create a new inventory item |
| PUT    | `/inventories/update/{id}` | Update an existing inventory item |
| PATCH  | `/inventories/updateStatus/{id}` | Update status of an inventory item (query parameter: `status`) |
| PATCH  | `/inventories/updatePricing/{id}` | Update pricing of an inventory item (query parameters: `costPrice`, `sellingPrice`) |
| PATCH  | `/inventories/updateAttribute/{id}` | Update specific attribute of an inventory item |
| DELETE | `/inventories/delete/{id}` | Delete an inventory item |

## Testing Strategy

Our testing approach includes:

1. **Unit Testing**: Testing individual components in isolation
2. **Integration Testing**: Testing interactions between components
3. **API Testing**: Ensuring REST endpoints function correctly
4. **Validation Testing**: Verifying input validation logic
5. **Edge Case Testing**: Handling boundary and error conditions

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Authors

- Anuj - Services
- Shreya - Model
- Abhishek - User
- Anurag - Controller

This project documentation was generated with assistance from GPT
