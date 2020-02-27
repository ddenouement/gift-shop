package com.giftshop.repository.interfaces;

import com.giftshop.dto.OrderDTO;
import com.giftshop.models.Order;
import com.giftshop.models.OrderItem;
import com.giftshop.models.ProductIdQuantityPair;

import java.util.ArrayList;

public interface IOrderDAO {
    Order getById(Integer orderId);
    ArrayList<OrderDTO> getAll();
    Integer insertOrder(OrderDTO order);
    void updateOrder(OrderDTO order);

    Integer getSum(ProductIdQuantityPair[] order);

    ArrayList<OrderItem> getOrderProductsById(Integer orderId);
}
