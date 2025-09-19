package com.dohuy.jpapart2.dto;

import com.dohuy.jpapart2.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;

    private String customerName;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;

    public static OrderDTO toDTO(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .build();
    }

    public static List<OrderDTO> toDTO(List<Order> orders) {
        return orders.stream()
                .map(OrderDTO::toDTO)
                .toList();
    }
}
