# 📦 OLX Inventory Management System

## 🚀 Project Overview

The **OLX Inventory Management System** is a backend service designed to manage the lifecycle of inventory items, particularly focusing on vehicles such as cars. Built with **Spring Boot**, the system offers robust CRUD operations, user authentication via **JWT**, and adheres to clean architectural principles ensuring scalability and maintainability.

---

## 🧩 Features

- **Inventory Management**: Comprehensive CRUD operations for inventory items.
- **Car-Specific Details**: Dedicated handling of car attributes through a separate `CarDetails` entity.
- **User Authentication**: Secure user registration and login mechanisms using JWT.
- **Pagination Support**: Efficient data retrieval with paginated responses.
- **Error Handling**: Consistent and meaningful error responses with appropriate HTTP status codes.
- **Layered Architecture**: Clear separation of concerns across controllers, services, and repositories.
- **High Test Coverage**: Designed with a focus on achieving 100% test coverage using JUnit and Mockito.

---

## 🗄️ Entity Overview

### 1. **Inventory**

Represents general inventory items.

- `sku` (Primary Key): Unique identifier for the inventory item.
- `type`: Type of inventory (e.g., "car").
- `primaryStatus`: Status of the inventory item (`CREATED`, `PROCURED`, `SOLD`).
- `primaryLocation`: Current location of the inventory item.
- `costPrice`: Acquisition cost.
- `sellingPrice`: Selling price.
- `createdAt` & `updatedAt`: Timestamps for record creation and updates.
- `createdBy` & `updatedBy`: References to the user responsible for creation and updates.
- **Relationship**: If `type` is "car", associates with the `CarDetails` entity.

### 2. **CarDetails**

Stores car-specific attributes.

- `id` (Primary Key): Unique identifier.
- `inventorySku` (Foreign Key): Links to the associated `Inventory` item.
- `vin`: Vehicle Identification Number.
- `make`: Manufacturer of the car.
- `model`: Model of the car.
- `trim`: Specific trim or variant.
- `year`: Manufacturing year.

### 3. **User**

Manages user authentication and authorization.

- `id` (Primary Key): Unique user identifier.
- `email`: User's email address.
- `password`: Hashed password for secure authentication.

---

## 🔐 Authentication & Authorization

- **Registration**: Users can register via the `/users/register` endpoint.
- **Login**: Authenticate using `/users/login` to receive a JWT.
- **Protected Routes**: All inventory-related endpoints require a valid JWT in the `Authorization` header.

---

## 📡 API Endpoints

### Inventory Management

| Endpoint                 | Method   | Description                                  |
|--------------------------|----------|----------------------------------------------|
| `/inventories`           | `POST`   | Create a new inventory item.                 |
| `/inventories/{sku}`     | `GET`    | Retrieve details of a specific inventory item by SKU. |
| `/inventories`           | `GET`    | List all inventory items with pagination support. |
| `/inventories/{sku}`     | `PATCH`  | Update details of an existing inventory item. |
| `/inventories/{sku}`     | `DELETE` | Remove an inventory item (if deletion is permitted). |

### User Management

| Endpoint         | Method | Description                  |
|------------------|--------|------------------------------|
| `/users/register`| `POST` | Register a new user account. |
| `/users/login`   | `POST` | Authenticate and receive a JWT. |

---

## ⚠️ Error Handling

The system returns structured error responses:

```json
{
  "errorCode": "ResourceNotFound",
  "errorMessage": "The requested inventory item was not found."
}
```

Common HTTP status codes include:

- `200 OK`: Successful operation.
- `201 Created`: Resource successfully created.
- `400 Bad Request`: Invalid input or request parameters.
- `401 Unauthorized`: Missing or invalid authentication token.
- `404 Not Found`: Requested resource does not exist.
- `500 Internal Server Error`: An unexpected error occurred on the server.

---

## 📁 Project Structure

```
inventories/
├── .gradle/                  # Gradle system files
├── .idea/                    # IntelliJ project config
├── gradle/                   # Gradle wrapper
├── src/
│   ├── main/
│   │   ├── java/com/olx/inventories/
│   │   │   └── InventoriesApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
│       └── java/com/olx/inventories/
│           └── InventoriesApplicationTests.java
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── .gitignore
├── README.md
└── Interns Project.pdf       # Project brief
```

---

## 🧪 Testing

- **Unit Tests**: Utilize JUnit and Mockito to test individual components.
- **Integration Tests**: Employ MockMvc to test API endpoints and their interactions.
- **Coverage Goal**: Strive for 100% test coverage to ensure reliability and robustness.

---

## 🛠️ Setup & Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/shreyaOLX/OLX_PROJECT.git
   ```

2. **Navigate to the Project Directory**:
   ```bash
   cd OLX_PROJECT
   ```

3. **Configure Application Properties**:
    - Set up your `application.properties` with the necessary database configurations and JWT secrets.

4. **Build the Project**:
   ```bash
   ./mvnw clean install
   ```

5. **Run the Application**:
   ```bash
   ./mvnw spring-boot:run
   ```

6. **API Documentation**:
    - Access the Swagger UI at `http://localhost:8080/swagger-ui.html` for interactive API documentation.

---

## ✅ Best Practices

- **Clean Code**: Adherence to SOLID principles and meaningful naming conventions.
- **DTO Utilization**: Separation between entity models and data transfer objects.
- **RESTful Design**: Proper use of HTTP verbs and status codes.
- **Security**: Implementation of JWT for stateless authentication.
- **Exception Handling**: Centralized management of exceptions to provide consistent responses.

---