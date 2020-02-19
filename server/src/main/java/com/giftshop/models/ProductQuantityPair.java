package com.giftshop.models;

public class ProductQuantityPair {
    private Product product;
    private Integer quantity;

    public ProductQuantityPair(Product p, Integer q){
        this.product=p;
        this.quantity=q;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}
