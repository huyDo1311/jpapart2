package com.dohuy.jpapart2.controller;

import com.dohuy.jpapart2.request.ProductRequest;
import com.dohuy.jpapart2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return productService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductRequest request) {
        return productService.create(request);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProductRequest request) {
        return productService.update(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return productService.delete(id);
    }
}
