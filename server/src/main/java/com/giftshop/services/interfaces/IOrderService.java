package com.giftshop.services.interfaces;

import com.giftshop.dto.OrderDTO;
import com.giftshop.models.Order;
import com.giftshop.models.ProductIdQuantityPair;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface IOrderService {
    Order getById(Integer orderId);
    ArrayList<Order> getAll();
    Integer insertOrder(OrderDTO order);
    void updateOrder(Order order);

    Integer getSum(ProductIdQuantityPair[] order);
}
