package com.dohuy.jpapart2.service;

import com.dohuy.jpapart2.dto.CategoryDTO;
import com.dohuy.jpapart2.dto.ProductDTO;
import com.dohuy.jpapart2.entity.Category;
import com.dohuy.jpapart2.entity.Product;
import com.dohuy.jpapart2.exception.CategoryExitsException;
import com.dohuy.jpapart2.exception.EmailExitsException;
import com.dohuy.jpapart2.exception.EntityNotFoundException;
import com.dohuy.jpapart2.repository.CategoryRepository;
import com.dohuy.jpapart2.repository.ProductRepository;
import com.dohuy.jpapart2.request.CategoryRequest;
import com.dohuy.jpapart2.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<?> createCategory(CategoryRequest request){
        if (categoryRepository.existsByName(request.getName())) {
            throw new CategoryExitsException("Category already exists: ");
        }
        Category category = new Category();
        category.setName(request.getName());

        categoryRepository.save(category);

        CategoryDTO categoryDTO = CategoryDTO.toDTO(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
    }

    public ResponseEntity<?> addProductToCategory(Long categoryId, ProductRequest request){

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("Category no found: " + categoryId)
        );

        boolean exists = category.getProducts().stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(request.getName()));

        if (exists) {
            return ResponseEntity
                    .badRequest()
                    .body("Product with name '" + request.getName() + "' already exists in this category.");
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setCategory(category);

        category.getProducts().add(product);
        categoryRepository.save(category);

//        productRepository.save(product)

        ProductDTO productDTO = ProductDTO.toDTO(product);

        return ResponseEntity.ok(productDTO);
    }

    public ResponseEntity<?> getProductsByCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + id));

        CategoryDTO categoryDTO = CategoryDTO.toDTOWithProducts(category);

        return ResponseEntity.ok(categoryDTO);

    }
}
