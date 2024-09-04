# Stock Management System

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Database Schema](#database-schema)
- [Setup Instructions](#setup-instructions)
- [How to Run](#how-to-run)
- [Usage](#usage)
- [Exception Handling](#exception-handling)

## Introduction
The Stock Management System is a menu-driven console application designed to manage products, suppliers, and orders. It serves as a practical demonstration of proficiency in Core Java, MySQL, and JDBC, showcasing capabilities in CRUD operations and exception handling.

## Features
- **Product Management**: Add, view, update, and delete products.
- **Supplier Management**: Add, view, update, and delete suppliers.
- **Order Management**: Place new orders, view and update order details, and cancel orders.
- **Database Integration**: Interact with a MySQL database to persist data.
- **Exception Handling**: User-friendly error messages and robust exception management.

## Technologies Used
- **Java**: Core Java for application logic.
- **MySQL**: Database management.
- **JDBC**: Java Database Connectivity for database interactions.
- **Eclipse IDE**: Development environment.

## Project Structure
The project follows a modular structure with packages for each functional area:

- `com.cts.client`: Contains the main entry point (`MainClient.java`) for the application.
- `com.cts.dao`: Interfaces for data access objects (DAO) for products, suppliers, and orders.
- `com.cts.dao.implement`: Implementation classes for DAO interfaces.
- `com.cts.model`: Data model classes for `Product`, `Supplier`, and `Order`.
- `com.cts.service`: Business logic for managing products, suppliers, and orders.
- `com.cts.service.implement`: Implementation of business logic classes.
- `com.cts.util`: Utility classes like `DatabaseConnection`.
- `com.cts.custom.exception`: Custom exceptions for handling specific error cases.

## Database Schema
### Product Table
- `product_id` (Primary Key)
- `name`
- `description`
- `price`
- `quantity_in_stock`

### Supplier Table
- `supplier_id` (Primary Key)
- `name`
- `email`
- `phone_number`
- `address`

### Order Table
- `order_id` (Primary Key)
- `product_id` (Foreign Key references `Product` table)
- `supplier_id` (Foreign Key references `Supplier` table)
- `order_date`
- `delivery_date`
- `status` (placed/delivered/cancelled)

## Setup Instructions
1. **Clone the repository**:  
   ```bash
   git clone https://github.com/your-repository-url.git
   cd StockManagementSystem

## How to Run
1. **Run the MainClient class**:
   - Navigate to `com.cts.client.MainClient` in Eclipse IDE.
   - Right-click and select "Run As" > "Java Application".

2. **Interact with the application**:
   - Follow the menu prompts to manage products, suppliers, and orders.

## Usage
### Product Management
- **Add a product**: Enter product details like name, description, price, and quantity.
- **View product**: Retrieve product information by entering the product ID.
- **Update product**: Modify product details.
- **Delete product**: Remove a product from the system.

### Supplier Management
- **Add a supplier**: Enter supplier details like name, email, phone number, and address.
- **View supplier**: Retrieve supplier information by entering the supplier ID.
- **Update supplier**: Modify supplier details.
- **Delete supplier**: Remove a supplier from the system.

### Order Management
- **Place an order**: Enter product and supplier IDs, order date, delivery date, and status.
- **View order**: Retrieve order details by entering the order ID.
- **Update order**: Modify order details.
- **Cancel order**: Remove an order from the system.

## Exception Handling
The system includes custom exception handling to manage common errors, such as:
- Invalid input data.
- Database connection issues.
- Non-existent product, supplier, or order IDs.
