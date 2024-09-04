package com.cts.dao;

import com.cts.model.Order;
import java.util.List;

public interface OrderDAO {
    void placeOrder(Order order);
    Order getOrderById(int orderId);
    List<Order> getAllOrders();
    void updateOrder(Order order);
    void cancelOrder(int orderId);
}
