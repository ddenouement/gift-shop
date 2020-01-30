package com.giftshop.models;

import java.io.Serializable;

public class WishListItem implements Serializable {
    private Product product;
    private User user;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
