package com.giftshop.models;

import java.io.Serializable;

public class OrderState implements Serializable {

    String NEW = "NEW";
    String INPROGRESS = "INPROGRESS";
    String CANCELLED = "CANCELLED";
    String DELIVERED = "DELIVERED";

    private Integer orderStateId;
    private String stateName;

    public Integer getOrderStateId() {
        return orderStateId;
    }

    public void setOrderStateId(Integer orderStateId) {
        this.orderStateId = orderStateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public OrderState(Integer orderStateId, String stateName){
        this.orderStateId = orderStateId;
        this.stateName = stateName;
    }
}
