package com.dohuy.jpapart2.controller;

import com.dohuy.jpapart2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/range")
    public ResponseEntity<?> getOrdersByDateRange(@RequestParam("start") String start,
                                                  @RequestParam("end") String end) {
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);
        return orderService.getOrdersByDateRange(startDate, endDate);
    }

    // GET /orders/top
    @GetMapping("/top")
    public ResponseEntity<?> getTopOrders() {
        return orderService.getTopOrders();
    }
}
