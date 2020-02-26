package com.giftshop.repository.interfaces;

import com.giftshop.models.Product;

import java.util.ArrayList;

public interface IProductDAO {
    Product getById(Integer productId);
    ArrayList<Product> getAll();
    ArrayList<Product> getByCategory(Integer categoryId);
    ArrayList<Product> getByCategories(ArrayList<Integer> categories);

    ArrayList<Product> getFromTo(Integer min, Integer max, Integer startRow, Integer endRow);
    ArrayList<Product> getByCategoryFromTo(Integer min, Integer max, Integer categoryId, Integer startRow, Integer endRow);
    ArrayList<Product> getByCategoriesFromTo(Integer min, Integer max, ArrayList<Integer> categories, Integer startRow, Integer endRow);



    Integer insertProduct(Product product);
    void updateProduct(Product product);

    Integer getAmount();
}
