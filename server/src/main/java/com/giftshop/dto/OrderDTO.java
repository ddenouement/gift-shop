package com.giftshop.dto;

import com.giftshop.models.OrderItem;
import com.giftshop.models.ProductIdQuantityPair;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderDTO {
    private Integer orderId;
    private Integer userId;
    private ArrayList<ProductIdQuantityPair> orderItems;
    private Integer orderState;
    private String address;
    private boolean cashPayment;
    private boolean postDelivery;
    private LocalDateTime orderDate;
    private BigInteger totalSum;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ArrayList<ProductIdQuantityPair> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<ProductIdQuantityPair> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderStateId(Integer orderState) {
        this.orderState = orderState;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(boolean cashPayment) {
        this.cashPayment = cashPayment;
    }


    public boolean getPostDelivery() {
        return postDelivery;
    }

    public void setPostDelivery(boolean postDelivery) {
        this.postDelivery = postDelivery;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigInteger getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigInteger totalSum) {
        this.totalSum = totalSum;
    }
}
