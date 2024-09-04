package com.cts.services;

import com.cts.dao.OrderDAO;
import com.cts.dao.impl.OrderDAOImpl;
import com.cts.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAOImpl();

    public void manageOrders(Scanner scanner) {
        while (true) {
            System.out.println("\nOrder Management");
            System.out.println("1. Place Order");
            System.out.println("2. View Order");
            System.out.println("3. View All Orders");
            System.out.println("4. Update Order");
            System.out.println("5. Cancel Order");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    placeOrder(scanner);
                    break;
                case 2:
                    viewOrder(scanner);
                    break;
                case 3:
                    viewAllOrders();
                    break;
                case 4:
                    updateOrder(scanner);
                    break;
                case 5:
                    cancelOrder(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void placeOrder(Scanner scanner) {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter Supplier ID: ");
        int supplierId = scanner.nextInt();
        System.out.print("Enter Order Date (YYYY-MM-DD): ");
        String orderDate = scanner.next();
        System.out.print("Enter Delivery Date (YYYY-MM-DD): ");
        String deliveryDate = scanner.next();
        System.out.print("Enter Order Status: ");
        String status = scanner.next();

        Order order = new Order(0, productId, supplierId, LocalDate.parse(orderDate), LocalDate.parse(deliveryDate), status);
        orderDAO.placeOrder(order);
        System.out.println("Order placed successfully.");
    }

    private void viewOrder(Scanner scanner) {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        Order order = orderDAO.getOrderById(orderId);
        if (order != null) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Product ID: " + order.getProductId());
            System.out.println("Supplier ID: " + order.getSupplierId());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Delivery Date: " + order.getDeliveryDate());
            System.out.println("Status: " + order.getStatus());
        } else {
            System.out.println("Order not found.");
        }
    }

    private void viewAllOrders() {
        List<Order> orders = orderDAO.getAllOrders();
        System.out.printf("%-10s %-10s %-10s %-15s %-15s %-15s\n", "ID", "Product ID", "Supplier ID", "Order Date", "Delivery Date", "Status");
        for (Order order : orders) {
            System.out.printf("%-10d %-10d %-10d %-15s %-15s %-15s\n",
                    order.getOrderId(), 
                    order.getProductId(), 
                    order.getSupplierId(),
                    order.getOrderDate(), 
                    order.getDeliveryDate(), 
                    order.getStatus());
        }
    }


    private void updateOrder(Scanner scanner) {
        System.out.print("Enter Order ID to Update: ");
        int orderId = scanner.nextInt();
        Order existingOrder = orderDAO.getOrderById(orderId);
        if (existingOrder != null) {
            System.out.print("Enter New Product ID: ");
            int productId = scanner.nextInt();
            System.out.print("Enter New Supplier ID: ");
            int supplierId = scanner.nextInt();
            System.out.print("Enter New Order Date (YYYY-MM-DD): ");
            String orderDate = scanner.next();
            System.out.print("Enter New Delivery Date (YYYY-MM-DD): ");
            String deliveryDate = scanner.next();
            System.out.print("Enter New Order Status: ");
            String status = scanner.next();

            existingOrder.setProductId(productId);
            existingOrder.setSupplierId(supplierId);
            existingOrder.setOrderDate(LocalDate.parse(orderDate));
            existingOrder.setDeliveryDate(LocalDate.parse(deliveryDate));
            existingOrder.setStatus(status);

            orderDAO.updateOrder(existingOrder);
            System.out.println("Order updated successfully.");
        } else {
            System.out.println("Order not found.");
        }
    }

    private void cancelOrder(Scanner scanner) {
        System.out.print("Enter Order ID to Cancel: ");
        int orderId = scanner.nextInt();
        orderDAO.cancelOrder(orderId);
        System.out.println("Order canceled successfully.");
    }
}
