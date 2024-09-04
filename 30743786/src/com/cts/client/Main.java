package com.cts.client;

import com.cts.services.ProductService;
import com.cts.services.SupplierService;
import com.cts.services.OrderService;
import com.cts.exception.InvalidChoiceException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();
        SupplierService supplierService = new SupplierService();
        OrderService orderService = new OrderService();

        while (true) {
            try {
                System.out.println("Stock Management System");
                System.out.println("1. Product Management");
                System.out.println("2. Supplier Management");
                System.out.println("3. Order Management");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = getValidChoice(scanner);

                switch (choice) {
                    case 1:
                        productService.manageProducts(scanner);
                        break;
                    case 2:
                        supplierService.manageSuppliers(scanner);
                        break;
                    case 3:
                        orderService.manageOrders(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        throw new InvalidChoiceException("Invalid choice. Please try again.");
                }
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private static int getValidChoice(Scanner scanner) throws InputMismatchException {
        if (!scanner.hasNextInt()) {
            throw new InputMismatchException();
        }
        return scanner.nextInt();
    }
}
