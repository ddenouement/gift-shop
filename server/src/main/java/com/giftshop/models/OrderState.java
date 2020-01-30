package com.giftshop.models;

import java.io.Serializable;

public class OrderState implements Serializable {
    private String orderStateId;
    private String stateName;

    public String getOrderStateId() {
        return orderStateId;
    }

    public void setOrderStateId(String orderStateId) {
        this.orderStateId = orderStateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
