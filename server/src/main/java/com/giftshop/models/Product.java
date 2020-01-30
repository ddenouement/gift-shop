package com.giftshop.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

    private String productId;
    private String productName;
    private ProductCategory category;
    private String productDescripton;
    private BigDecimal productPrice;
    private boolean isAvailable;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
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

    //Contrustors
    public Product(String productId, String productName, String productDescripton, ProductCategory category,
                   BigDecimal productPrice, boolean isAvailable){
        super();
        this.productId = productId;
        this.productName = productName;
        this.productDescripton = productDescripton;
        this.category = category;
        this.productPrice = productPrice;
        this.isAvailable = isAvailable;

    }
    public Product(){

    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
