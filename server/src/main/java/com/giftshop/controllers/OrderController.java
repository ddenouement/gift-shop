package com.giftshop.controllers;

import com.giftshop.config.TokenProvider;
import com.giftshop.dto.OrderDTO;
import com.giftshop.models.Order;
import com.giftshop.models.ProductIdQuantityPair;
import com.giftshop.services.OrderService;
import com.giftshop.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    private IOrderService orderService;
    private TokenProvider jwtTokenProvider;
    @Autowired
    OrderController(final OrderService orderService,final TokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
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
    public ResponseEntity insertOrder(@CookieValue("token") String token,  @RequestBody OrderDTO order){

        Integer my_id = jwtTokenProvider.getUserId(token);
        order.setUserId(my_id);

        if(orderService.insertOrder((order)) == -1) return badRequest().body("Error");
        return ResponseEntity.ok().build();
    }
    @PostMapping("/orders/checkSum")
    public Integer checkSum(@RequestBody ProductIdQuantityPair[] order){
        System.out.println(order.length);
        return orderService.getSum((order)) ;
    }

    @PutMapping("/orders")
    public ResponseEntity updateOrder(@RequestBody Order order){
        orderService.updateOrder(order);
        return ResponseEntity.ok().build();
    }
}
