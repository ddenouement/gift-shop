package com.giftshop.repository.interfaces;

import com.giftshop.models.Category;

import java.util.ArrayList;

public interface ICategoryDAO  {
    Category getById(Integer categoryId);
    ArrayList <Category> getAllCategories();

    Integer createCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Integer categoryId);

}
