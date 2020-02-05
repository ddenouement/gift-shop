package com.giftshop.controllers;

import com.giftshop.models.Product;
import com.giftshop.services.ProductService;
import com.giftshop.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private IProductService productService;

    @Autowired
    ProductController(
            final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public Iterable<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/products/{product-id}")
    public Product getById(@PathVariable("product-id") Integer productId) {
        return productService.getById(productId);
    }

    @GetMapping("/products/category/{category-id}")
    public Iterable<Product> getByCategory(@PathVariable("category-id") Integer categoryId) {
        return productService.getByCategory(categoryId);
    }

    @GetMapping("/products/category")
    public Iterable<Product> getByCategories(@RequestParam("categories") Integer[] categories) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Collections.addAll(list, categories);

        return productService.getByCategories(list);
    }

    @PostMapping("/products")
    public ResponseEntity insertProduct(@Valid @RequestBody Product product, final HttpServletResponse response) {
        if (productService.insertProduct(product)==0) return badRequest().body("Error");

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);

        return ResponseEntity.ok().build();
    }
}
