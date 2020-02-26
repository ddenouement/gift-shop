package com.giftshop.repository.interfaces;

import com.giftshop.dto.OrderDTO;
import com.giftshop.models.Order;
import com.giftshop.models.OrderItem;

import java.util.ArrayList;

public interface IOrderDAO {
    Order getById(Integer orderId);
    ArrayList<OrderDTO> getAll();
    Integer insertOrder(OrderDTO order);
    void updateOrder(Order order);

    Integer getSum(OrderItem[] order);

    ArrayList<OrderItem> getOrderProductsById(Integer orderId);
}
