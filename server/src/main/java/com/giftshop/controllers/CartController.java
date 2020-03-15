package com.giftshop.controllers;

import com.giftshop.config.TokenProvider;
import com.giftshop.models.ProductIdQuantityPair;
import com.giftshop.services.OrderService;
import com.giftshop.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin
public class CartController {
    private IOrderService orderService;
    private TokenProvider jwtTokenProvider;
    @Autowired
    CartController(final OrderService orderService, final TokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.orderService = orderService;
    }

    @PostMapping("/cart/checkSum")
    public Integer checkSum(@RequestBody ProductIdQuantityPair[] order){
        return orderService.getSum((order)) ;
    }
}
