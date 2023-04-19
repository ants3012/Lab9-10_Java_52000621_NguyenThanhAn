package vn.edu.tdtu.lab09_10.service;

import vn.edu.tdtu.lab09_10.model.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getAllOrders();

    public Order saveOrder(Order order);

    public Order getOrderById(Integer id);

    public Order updateOrder(Order order);

    void deleteOrderById(Integer id);
}