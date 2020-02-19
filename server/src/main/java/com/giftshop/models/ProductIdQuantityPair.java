package com.giftshop.models;

public class ProductIdQuantityPair {
    private Integer productId;
    private Integer quantity;

    public ProductIdQuantityPair(Integer p, Integer q){
        this.productId=p;
        this.quantity=q;
    }
    public void setProduct(Integer product) {
        this.productId = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getProductId() {
        return productId;
    }
}

