# Phasezero Catalog Service

A Spring Boot microservice that manages a product catalog.  
This project is developed as part of the PHASEZERO Backend Java Assignment.

---

## 📌 Features (Required APIs)
The application provides the following operations:

1. **Add a Product**  
2. **List All Products**  
3. **Search Products by Name** (case-insensitive)
4. **Filter Products by Category**
5. **Sort Products by Price (Ascending)**
6. **Calculate Total Inventory Value**

---

## 🏗️ Project Structure
The project follows a clean layered architecture:


### Layers:
- **Controller Layer**  
  Handles all REST endpoints.

- **Service Layer**  
  Contains business logic and validations.

- **DAO Layer**  
  Bridges service and repository.

- **Repository Layer (JPA)**  
  Handles database operations using H2.

- **Entity Layer**  
  Defines the Product model.

- **Exception Handling**  
  Custom exceptions + consistent ResponseStructure.

---

## 🧱 Data Model (Product)

| Field       | Type    | Description                        |
|-------------|---------|------------------------------------|
| id          | Integer | Auto-generated primary key         |
| partNumber  | String  | Unique business key                |
| partName    | String  | Always stored in lowercase         |
| category    | String  | Product category                   |
| price       | Double  | Must be non-negative               |
| stock       | Integer | Must be non-negative               |

---

## 🛠️ Technology Stack
- Java 17  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- H2 In-memory Database  
- Lombok  

---

## ⚙️ How to Run the Application

### 1. Clone the Repository

**JDBC URL:** `jdbc:h2:mem:ProductDB`  
**Username:** `sa`  
**Password:** *(empty)*

---

## 📚 API Endpoints

### 1️⃣ Add Product
