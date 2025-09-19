package com.dohuy.jpapart2.dto;

import com.dohuy.jpapart2.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Long id;
    private String name;
    private String phone;


    public static CustomerDTO toDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .build();
    }

    public static List<CustomerDTO> toDTOList(List<Customer> customers){
        return customers.stream()
                .map(CustomerDTO::toDTO)
                .toList();
    }
}
