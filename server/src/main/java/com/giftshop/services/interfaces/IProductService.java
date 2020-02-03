package com.giftshop.services.interfaces;

import com.giftshop.models.Product;

import java.util.ArrayList;

public interface IProductService {
    Product getById(Integer productId);
    ArrayList<Product> getAll();
    ArrayList<Product> getByCategory(Integer categoryId);
    ArrayList<Product> getByCategories(ArrayList<Integer> categories);

    Integer insertProduct(Product product);
    void updateProduct(Product product);
}
