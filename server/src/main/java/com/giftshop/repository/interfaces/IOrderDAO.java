package com.giftshop.repository.interfaces;

import com.giftshop.dto.OrderDTO;
import com.giftshop.models.Order;
import com.giftshop.models.ProductIdQuantityPair;

import java.util.ArrayList;

public interface IOrderDAO {
    Order getById(Integer orderId);
    ArrayList<Order> getAll();
    Integer insertOrder(OrderDTO order);
    void updateOrder(Order order);

    Integer getSum(ProductIdQuantityPair[] order);
}
