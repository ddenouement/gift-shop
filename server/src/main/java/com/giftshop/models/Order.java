package com.giftshop.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Dictionary;

public class Order implements Serializable {
    private Integer orderId;
    private Integer userId;
    private Dictionary<Product, Integer> orderItems;
    private OrderState orderState;
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

    public Integer getUser() {
        return userId;
    }

    public void setUser(Integer userId) {
        this.userId = userId;
    }

    public Dictionary<Product, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Dictionary<Product, Integer>orderItems) {
        this.orderItems = orderItems;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderStateId(OrderState orderState) {
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
