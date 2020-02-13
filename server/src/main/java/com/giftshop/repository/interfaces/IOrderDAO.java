package com.giftshop.repository.interfaces;

import com.giftshop.models.Order;

import java.util.ArrayList;

public interface IOrderDAO {
    Order getById(Integer orderId);
    ArrayList<Order> getAll();
    Integer insertOrder(Order order);
    void updateOrder(Order order);

}
