package com.giftshop.services.interfaces;

import com.giftshop.models.Category;

import java.util.ArrayList;

public interface ICategoryService {
    Category getById(Integer categoryId);
    ArrayList<Category> getAllCategories();

    Integer createCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Integer categoryId);
}
