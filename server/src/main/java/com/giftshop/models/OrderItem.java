package com.giftshop.models;

public class OrderItem {

    private Product product;
    private Integer quantity;
    private Integer savedPrice;

    public OrderItem(Product p, Integer q, Integer savedPrice){
        this.product =p;
        this.quantity=q;
        this.savedPrice = savedPrice;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setSavedPrice(Integer savedPrice) {
        this.savedPrice = savedPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getSavedPrice() {
        return savedPrice;
    }

}
