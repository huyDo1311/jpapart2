package com.dohuy.jpapart2.controller;

import com.dohuy.jpapart2.request.CategoryRequest;
import com.dohuy.jpapart2.request.ProductRequest;
import com.dohuy.jpapart2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest request){
        return categoryService.createCategory(request);
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<?> addProductToCategory(@PathVariable Long id, @RequestBody ProductRequest request) {
        return categoryService.addProductToCategory(id,request);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<?> getProductsByCategory(@PathVariable Long id) {
        return categoryService.getProductsByCategory(id);
    }

}
