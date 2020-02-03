package com.giftshop.services;

import com.giftshop.models.Product;
import com.giftshop.repository.ProductDAO;
import com.giftshop.repository.interfaces.IProductDAO;
import com.giftshop.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService implements IProductService {

    private IProductDAO productDAO;

    @Autowired
    ProductService(final ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product getById(Integer productId) {
        return productDAO.getById(productId);
    }

    @Override
    public ArrayList<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public ArrayList<Product> getByCategory(Integer categoryId) {
        return productDAO.getByCategory(categoryId);
    }

    @Override
    public ArrayList<Product> getByCategories(ArrayList<Integer> categories) {
        return productDAO.getByCategories(categories);
    }

    @Override
    public Integer insertProduct(Product product) {
        return productDAO.insertProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }
}
