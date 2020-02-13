package com.giftshop.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Product implements Serializable {

    private Integer productId;
    private String productName;
    private ArrayList<Integer> categories;
    private String description;
    private BigDecimal price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        isAvailable = isAvailable;
    }

    //Construstors
    public Product(Integer productId, String productName, String description, ArrayList<Integer> categories,
                   BigDecimal price, boolean isAvailable){
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
