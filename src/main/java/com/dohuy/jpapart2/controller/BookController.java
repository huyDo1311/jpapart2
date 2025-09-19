package com.dohuy.jpapart2.controller;

import com.dohuy.jpapart2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public ResponseEntity<?> searchByAuthor(@RequestParam(required = false) String author,
                                            @RequestParam(required = false) Double minPrice,
                                            @RequestParam(required = false) Double maxPrice) {
        if (author != null) {
            return bookService.searchByAuthor(author);
        } else if (minPrice != null && maxPrice != null) {
            return bookService.filterByPrice(minPrice, maxPrice);
        }
        return ResponseEntity.badRequest().body("Thiếu tham số tìm kiếm");
    }
}
