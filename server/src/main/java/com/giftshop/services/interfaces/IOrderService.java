package com.giftshop.services.interfaces;

import com.giftshop.dto.OrderDTO;
import com.giftshop.models.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface IOrderService {
    Order getById(Integer orderId);
    ArrayList<OrderDTO> getAll();
    Integer insertOrder(OrderDTO order);
    void updateOrder(OrderDTO order);

    Integer getSum(ProductIdQuantityPair[] order);

    ArrayList<OrderItem> getOrderProductsById(Integer orderId);
}
