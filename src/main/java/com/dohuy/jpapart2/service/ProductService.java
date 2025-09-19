package com.dohuy.jpapart2.service;

import com.dohuy.jpapart2.dto.ProductDTO;
import com.dohuy.jpapart2.entity.Product;
import com.dohuy.jpapart2.exception.EntityNotFoundException;
import com.dohuy.jpapart2.repository.ProductRepository;
import com.dohuy.jpapart2.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> getAll() {
        List<ProductDTO> products = productRepository.findAll()
                .stream()
                .map(ProductDTO::toDTOWithCategory)
                .toList();
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<?> getById(Long id){
        var existing = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not Found")
        );
        ProductDTO productDTO = ProductDTO.toDTO(existing);
        return ResponseEntity.ok(productDTO);
    }

    public ResponseEntity<?> create(ProductRequest request){

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());

        productRepository.save(product);

        ProductDTO productDTO = ProductDTO.toDTO(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    public ResponseEntity<?> update(ProductRequest request){

        var existing = productRepository.findById(request.getId()).orElseThrow(
                () -> new EntityNotFoundException("Not found")
        );

        existing.setName(request.getName());
        existing.setPrice(request.getPrice());
        existing.setDescription(request.getDescription());
        productRepository.save(existing);

        ProductDTO productDTO = ProductDTO.toDTO(existing);

        return ResponseEntity.ok(productDTO);
    }

    public ResponseEntity<?> delete(Long id) {

        var existing = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not Found")
        );

        productRepository.delete(existing);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted");
    }
}
