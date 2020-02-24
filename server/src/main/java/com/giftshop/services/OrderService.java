package com.giftshop.services;

import com.giftshop.dto.OrderDTO;
import com.giftshop.models.Order;
import com.giftshop.models.ProductIdQuantityPair;
import com.giftshop.models.ProductQuantityPair;
import com.giftshop.repository.OrderDAO;
import com.giftshop.repository.interfaces.IOrderDAO;
import com.giftshop.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService implements IOrderService {
    private IOrderDAO orderDAO;

    @Autowired
    OrderService(final OrderDAO orderDAO){this.orderDAO = orderDAO;}

    @Override
    public Order getById(Integer orderId) {
        return orderDAO.getById(orderId);
    }

    @Override
    public ArrayList<OrderDTO> getAll() {
        return orderDAO.getAll();
    }

    @Override
    public Integer insertOrder(OrderDTO order) {
        return orderDAO.insertOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public Integer getSum(ProductIdQuantityPair[] order) {
        return  orderDAO.getSum(order);
    }

    @Override
    public ArrayList<ProductQuantityPair> getOrderProductsById(Integer orderId) {
        return orderDAO.getOrderProductsById(orderId);
    }
}
