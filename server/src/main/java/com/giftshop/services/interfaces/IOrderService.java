package com.giftshop.services.interfaces;

import com.giftshop.models.Order;

import java.util.ArrayList;

public interface IOrderService {
    Order getById(Integer orderId);
    ArrayList<Order> getAll();
    Integer insertOrder(Order order);
    void updateOrder(Order order);
}
