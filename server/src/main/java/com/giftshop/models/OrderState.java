package com.giftshop.models;

import java.io.Serializable;

public class OrderState implements Serializable {
    private int orderStateId;
    private String stateName;

    public int getOrderStateId() {
        return orderStateId;
    }

    public void setOrderStateId(int orderStateId) {
        this.orderStateId = orderStateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
