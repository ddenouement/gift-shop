package com.giftshop.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Product implements Serializable {

    private Integer productId;
    private String productName;
    private ArrayList<Category> categories;
    private String productDescripton;
    private BigDecimal productPrice;
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

    public String getProductDescripton() {
        return productDescripton;
    }

    public void setProductDescripton(String productDescripton) {
        this.productDescripton = productDescripton;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    //Construstors
    public Product(Integer productId, String productName, String productDescripton, ArrayList<Category> categories,
                   BigDecimal productPrice, boolean isAvailable){
        super();
        this.productId = productId;
        this.productName = productName;
        this.productDescripton = productDescripton;
        this.categories = categories;
        this.productPrice = productPrice;
        this.isAvailable = isAvailable;

    }
    public Product(){

    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
