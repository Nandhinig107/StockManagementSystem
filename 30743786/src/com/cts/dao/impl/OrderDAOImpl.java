package com.cts.dao.impl;

import com.cts.dao.OrderDAO;
import com.cts.model.Order;
import com.cts.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public void placeOrder(Order order) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO `Order` (product_id, supplier_id, order_date, delivery_date, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getProductId());
            preparedStatement.setInt(2, order.getSupplierId());
            preparedStatement.setDate(3, Date.valueOf(order.getOrderDate()));
            preparedStatement.setDate(4, Date.valueOf(order.getDeliveryDate()));
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.executeUpdate();

            // Update the stock quantity after placing the order
            updateStockQuantity(order.getProductId(), -1);  // Reduce the quantity by 1
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM `Order` WHERE order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = new Order(
                        resultSet.getInt("order_id"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("supplier_id"),
                        resultSet.getDate("order_date").toLocalDate(),
                        resultSet.getDate("delivery_date").toLocalDate(),
                        resultSet.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM `Order`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                orders.add(new Order(
                        resultSet.getInt("order_id"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("supplier_id"),
                        resultSet.getDate("order_date").toLocalDate(),
                        resultSet.getDate("delivery_date").toLocalDate(),
                        resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void updateOrder(Order order) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE `Order` SET product_id = ?, supplier_id = ?, order_date = ?, delivery_date = ?, status = ? WHERE order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getProductId());
            preparedStatement.setInt(2, order.getSupplierId());
            preparedStatement.setDate(3, Date.valueOf(order.getOrderDate()));
            preparedStatement.setDate(4, Date.valueOf(order.getDeliveryDate()));
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.setInt(6, order.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelOrder(int orderId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Order order = getOrderById(orderId);
            if (order != null) {
                String query = "DELETE FROM `Order` WHERE order_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, orderId);
                preparedStatement.executeUpdate();

                // Update the stock quantity after canceling the order
                updateStockQuantity(order.getProductId(), 1);  // Increase the quantity by 1
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateStockQuantity(int productId, int quantityChange) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE Product SET quantity_in_stock = quantity_in_stock + ? WHERE product_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quantityChange);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
