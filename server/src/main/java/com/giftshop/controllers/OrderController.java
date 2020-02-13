package com.giftshop.controllers;

import com.giftshop.models.Order;
import com.giftshop.services.OrderService;
import com.giftshop.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    private IOrderService orderService;

    @Autowired
    OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/{order-id}")
    public Order GetOrderById(@PathVariable("order-id") Integer orderId){
        return orderService.getById(orderId);
    }

    @GetMapping("/orders")
    public Iterable<Order> getAll(){
        return orderService.getAll();
    }

    @PostMapping("/orders")
    public ResponseEntity insertOrder(@Valid @RequestBody Order order, final HttpServletResponse response){
        if(orderService.insertOrder((order)) == 0) return badRequest().body("Error");
        return ResponseEntity.ok().build();
    }

    @PutMapping("orders")
    public ResponseEntity updateOrder(@RequestBody Order order){
        orderService.updateOrder(order);
        return ResponseEntity.ok().build();
    }
}
