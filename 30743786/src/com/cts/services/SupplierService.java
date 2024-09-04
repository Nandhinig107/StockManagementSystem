package com.cts.services;

import com.cts.dao.SupplierDAO;
import com.cts.dao.impl.SupplierDAOImpl;
import com.cts.model.Supplier;

import java.util.List;
import java.util.Scanner;

public class SupplierService {
    private SupplierDAO supplierDAO = new SupplierDAOImpl();

    public void manageSuppliers(Scanner scanner) {
        while (true) {
            System.out.println("\nSupplier Management");
            System.out.println("1. Add Supplier");
            System.out.println("2. View Supplier");
            System.out.println("3. View All Suppliers");
            System.out.println("4. Update Supplier");
            System.out.println("5. Delete Supplier");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addSupplier(scanner);
                    break;
                case 2:
                    viewSupplier(scanner);
                    break;
                case 3:
                    viewAllSuppliers();
                    break;
                case 4:
                    updateSupplier(scanner);
                    break;
                case 5:
                    deleteSupplier(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addSupplier(Scanner scanner) {
        System.out.print("Enter Supplier Name: ");
        String name = scanner.next();
        System.out.print("Enter Supplier Email: ");
        String email = scanner.next();
        System.out.print("Enter Supplier Phone Number: ");
        String phoneNumber = scanner.next();
        System.out.print("Enter Supplier Address: ");
        String address = scanner.next();

        Supplier supplier = new Supplier(0, name, email, phoneNumber, address);
        supplierDAO.addSupplier(supplier);
        System.out.println("Supplier added successfully.");
    }

    private void viewSupplier(Scanner scanner) {
        System.out.print("Enter Supplier ID: ");
        int supplierId = scanner.nextInt();
        Supplier supplier = supplierDAO.getSupplierById(supplierId);
        if (supplier != null) {
            System.out.println("Supplier ID: " + supplier.getSupplierId());
            System.out.println("Name: " + supplier.getName());
            System.out.println("Email: " + supplier.getEmail());
            System.out.println("Phone Number: " + supplier.getPhoneNumber());
            System.out.println("Address: " + supplier.getAddress());
        } else {
            System.out.println("Supplier not found.");
        }
    }

    private void viewAllSuppliers() {
        List<Supplier> suppliers = supplierDAO.getAllSuppliers();
        System.out.printf("%-10s %-20s %-30s %-20s %-30s\n", "ID", "Name", "Email", "Phone", "Address");
        for (Supplier supplier : suppliers) {
            System.out.printf("%-10d %-20s %-30s %-20s %-30s\n",
                    supplier.getSupplierId(), supplier.getName(), supplier.getEmail(),
                    supplier.getPhoneNumber(), supplier.getAddress());
        }
    }

    private void updateSupplier(Scanner scanner) {
        System.out.print("Enter Supplier ID to Update: ");
        int supplierId = scanner.nextInt();
        Supplier existingSupplier = supplierDAO.getSupplierById(supplierId);
        if (existingSupplier != null) {
            System.out.print("Enter New Name: ");
            String name = scanner.next();
            System.out.print("Enter New Email: ");
            String email = scanner.next();
            System.out.print("Enter New Phone Number: ");
            String phoneNumber = scanner.next();
            System.out.print("Enter New Address: ");
            String address = scanner.next();

            existingSupplier.setName(name);
            existingSupplier.setEmail(email);
            existingSupplier.setPhoneNumber(phoneNumber);
            existingSupplier.setAddress(address);

            supplierDAO.updateSupplier(existingSupplier);
            System.out.println("Supplier updated successfully.");
        } else {
            System.out.println("Supplier not found.");
        }
    }

    private void deleteSupplier(Scanner scanner) {
        System.out.print("Enter Supplier ID to Delete: ");
        int supplierId = scanner.nextInt();
        supplierDAO.deleteSupplier(supplierId);
        System.out.println("Supplier deleted successfully.");
    }
}
