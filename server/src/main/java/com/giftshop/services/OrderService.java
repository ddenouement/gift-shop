package com.giftshop.services;

import com.giftshop.models.Order;
import com.giftshop.repository.OrderDAO;
import com.giftshop.repository.interfaces.IOrderDAO;
import com.giftshop.services.interfaces.IOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService implements IOrderService {
    private IOrderDAO orderDAO;

    OrderService(final OrderDAO orderDAO){this.orderDAO = orderDAO;}

    @Override
    public Order getById(Integer orderId) {
        return orderDAO.getById(orderId);
    }

    @Override
    public ArrayList<Order> getAll() {
        return orderDAO.getAll();
    }

    @Override
    public Integer insertOrder(Order order) {
        return orderDAO.insertOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }
}
