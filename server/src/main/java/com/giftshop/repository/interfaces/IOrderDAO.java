package com.giftshop.repository.interfaces;

import com.giftshop.dto.OrderDTO;
import com.giftshop.models.Order;
import com.giftshop.models.ProductIdQuantityPair;
import com.giftshop.models.ProductQuantityPair;

import java.util.ArrayList;

public interface IOrderDAO {
    Order getById(Integer orderId);
    ArrayList<OrderDTO> getAll();
    Integer insertOrder(OrderDTO order);
    void updateOrder(Order order);

    Integer getSum(ProductIdQuantityPair[] order);

    ArrayList<ProductQuantityPair> getOrderProductsById(Integer orderId);
}
