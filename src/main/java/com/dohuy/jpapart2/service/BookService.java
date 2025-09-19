package com.dohuy.jpapart2.service;

import com.dohuy.jpapart2.entity.Book;
import com.dohuy.jpapart2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<?> searchByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return ResponseEntity.ok(books);
    }

    public ResponseEntity<?> filterByPrice(Double minPrice, Double maxPrice) {
        List<Book> books = bookRepository.findByPriceBetween(minPrice, maxPrice);
        return ResponseEntity.ok(books);
    }
}
