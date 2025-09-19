package com.dohuy.jpapart2.service;

import com.dohuy.jpapart2.dto.OrderDTO;
import com.dohuy.jpapart2.entity.Order;
import com.dohuy.jpapart2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<?> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orders = orderRepository.findByCreatedAtBetween(startDate, endDate);
        List<OrderDTO> orderDTOs = OrderDTO.toDTO(orders);
        return ResponseEntity.ok(orderDTOs);
    }

    public ResponseEntity<?> getTopOrders() {
        List<Order> orders = orderRepository.findTop5ByOrderByTotalAmountDesc();
        List<OrderDTO> orderDTOs = OrderDTO.toDTO(orders);
        return ResponseEntity.ok(orderDTOs);
    }
}
