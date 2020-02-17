package com.giftshop.controllers;

import com.giftshop.models.Category;
import com.giftshop.services.CategoryService;
import com.giftshop.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private ICategoryService categoryService;

    @Autowired
    CategoryController(final CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public Iterable<Category> getAll() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{category-id}")
    public Category getById(@PathVariable("category-id") Integer categoryId){
        return categoryService.getById(categoryId);
    }

    @PostMapping("/categories")
    public ResponseEntity createCategory(@Valid @RequestBody Category category, final HttpServletResponse response) {
        if (categoryService.createCategory(category)==0) return badRequest().body("Error");

        return ResponseEntity.ok().build();
    }

    @PutMapping("/categories")
    public ResponseEntity updateCategory(@RequestBody Category category) {
        categoryService.updateCategory(category);

        return ResponseEntity.ok().build();
    }

}
