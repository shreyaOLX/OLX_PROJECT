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
| GET    | `/api/inventory` | Retrieve all inventory items |
| GET    | `/api/inventory/{id}` | Retrieve a specific inventory item by ID |
| POST   | `/api/inventory` | Create a new inventory item |
| PUT    | `/api/inventory/{id}` | Update an existing inventory item |
| DELETE | `/api/inventory/{id}` | Delete an inventory item |
| GET    | `/api/inventory/type/{type}` | Get items by type (CAR, BIKE, etc.) |
| GET    | `/api/inventory/status/{status}` | Get items by status (CREATED, PROCURED, etc.) |
| GET    | `/api/inventory/search` | Search inventory items by name (query parameter: `name`) |
| GET    | `/api/inventory/low-stock` | Get items with quantity below threshold |
| POST   | `/api/inventory/bulk` | Bulk create inventory items |
| PUT    | `/api/inventory/{id}/status` | Update status of an inventory item |
| PUT    | `/api/inventory/{id}/quantity` | Update quantity of an inventory item |

### Response Format

All API responses follow a consistent format:

```json
{
  "status": "SUCCESS",
  "message": "Operation completed successfully",
  "data": {
    // Response data here
  }
}
```

### Error Handling

Errors are returned with appropriate HTTP status codes and detailed error messages:

```json
{
  "status": "ERROR",
  "message": "Unable to process request",
  "errors": [
    "Validation error 1",
    "Validation error 2"
  ]
}
```

## Testing Strategy

Our testing approach includes:

1. **Unit Testing**: Testing individual components in isolation
2. **Integration Testing**: Testing interactions between components
3. **API Testing**: Ensuring REST endpoints function correctly
4. **Validation Testing**: Verifying input validation logic
5. **Edge Case Testing**: Handling boundary and error conditions

## Getting Started

### Prerequisites
- Java JDK 11 or higher
- Gradle
- Your preferred IDE (IntelliJ IDEA, Eclipse, VS Code)

### Setup and Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/inventory-management.git
   ```

2. Navigate to the project directory:
   ```
   cd inventory-management
   ```

3. Build the project:
   ```
   gradle build
   ```

4. Run the application:
   ```
   gradle bootRun
   ```

5. Access the application:
   ```
   http://localhost:8080
   ```

## Contributing

We welcome contributions to this project. Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Authors

- Anuj - Services
- Shreya - Model
- Abhishek - User
- Anurag - Controller