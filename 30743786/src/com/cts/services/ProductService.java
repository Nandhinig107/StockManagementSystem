package com.cts.services;

import com.cts.dao.ProductDAO;
import com.cts.dao.impl.ProductDAOImpl;
import com.cts.model.Product;

import java.util.List;
import java.util.Scanner;

public class ProductService {
    private ProductDAO productDAO = new ProductDAOImpl();

    public void manageProducts(Scanner scanner) {
        while (true) {
            System.out.println("\nProduct Management");
            System.out.println("1. Add Product");
            System.out.println("2. View Product");
            System.out.println("3. View All Products");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    viewProduct(scanner);
                    break;
                case 3:
                    viewAllProducts();
                    break;
                case 4:
                    updateProduct(scanner);
                    break;
                case 5:
                    deleteProduct(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addProduct(Scanner scanner) {
        System.out.print("Enter Product Name: ");
        String name = scanner.next();
        System.out.print("Enter Product Description: ");
        String description = scanner.next();
        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Quantity in Stock: ");
        int quantity = scanner.nextInt();

        Product product = new Product(0, name, description, price, quantity);
        productDAO.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private void viewProduct(Scanner scanner) {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        Product product = productDAO.getProductById(productId);
        if (product != null) {
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Name: " + product.getName());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Quantity in Stock: " + product.getQuantityInStock());
        } else {
            System.out.println("Product not found.");
        }
    }

    private void viewAllProducts() {
        List<Product> products = productDAO.getAllProducts();
        System.out.printf("%-10s %-20s %-30s %-10s %-10s\n", "ID", "Name", "Description", "Price", "Stock");
        for (Product product : products) {
            System.out.printf("%-10d %-20s %-30s %-10.2f %-10d\n",
                    product.getProductId(), product.getName(), product.getDescription(),
                    product.getPrice(), product.getQuantityInStock());
        }
    }
    private void updateProduct(Scanner scanner) {
        System.out.print("Enter Product ID to Update: ");
        int productId = scanner.nextInt();
        Product existingProduct = productDAO.getProductById(productId);
        if (existingProduct != null) {
            System.out.print("Enter New Name: ");
            String name = scanner.next();
            System.out.print("Enter New Description: ");
            String description = scanner.next();
            System.out.print("Enter New Price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter New Quantity in Stock: ");
            int quantity = scanner.nextInt();

            existingProduct.setName(name);
            existingProduct.setDescription(description);
            existingProduct.setPrice(price);
            existingProduct.setQuantityInStock(quantity);

            productDAO.updateProduct(existingProduct);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private void deleteProduct(Scanner scanner) {
        System.out.print("Enter Product ID to Delete: ");
        int productId = scanner.nextInt();
        productDAO.deleteProduct(productId);
        System.out.println("Product deleted successfully.");
    }
}
