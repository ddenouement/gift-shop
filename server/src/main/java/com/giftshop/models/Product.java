package com.giftshop.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Product implements Serializable {

    private Integer productId;
    private String productName;
    private ArrayList<Integer> categories;
    private String description;
    private Integer price;
    private boolean isAvailable;
    private String photo;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    //Construstors
    public Product(Integer productId, String productName, String description, ArrayList<Integer> categories,
                   Integer price, boolean isAvailable){
        super();
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.categories = categories;
        this.price = price;
        this.isAvailable = isAvailable;

    }
    public Product(){

    }

    public ArrayList<Integer> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Integer> categories) {
        this.categories = categories;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
