package com.giftshop.services;

import com.giftshop.models.Category;
import com.giftshop.repository.CategoryDAO;
import com.giftshop.repository.interfaces.ICategoryDAO;
import com.giftshop.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService implements ICategoryService {

    private ICategoryDAO categoryDAO;

    @Autowired
    CategoryService(final CategoryDAO categoryDAO){this.categoryDAO = categoryDAO;}

    @Override
    public Category getById(Integer categoryId) {
        return categoryDAO.getById(categoryId);
    }

    @Override
    public ArrayList<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    @Override
    public Integer createCategory(Category category) {
        return categoryDAO.createCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryDAO.deleteCategory(category);
    }
}
