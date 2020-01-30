package com.giftshop.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements Serializable {
    private String orderId;
    private User user;
    private ArrayList<OrderItem> orderItems;
    private OrderState orderState;
    private String address;
    private String cashPayment;
    private String postDelivery;
    private LocalDate orderDate;
    private BigInteger totalSum;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
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

    public String getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(String cashPayment) {
        this.cashPayment = cashPayment;
    }


    public String getPostDelivery() {
        return postDelivery;
    }

    public void setPostDelivery(String postDelivery) {
        this.postDelivery = postDelivery;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigInteger getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigInteger totalSum) {
        this.totalSum = totalSum;
    }
}
