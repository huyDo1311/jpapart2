package com.dohuy.jpapart2.service;

import com.dohuy.jpapart2.dto.CustomerDTO;
import com.dohuy.jpapart2.entity.Customer;
import com.dohuy.jpapart2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = CustomerDTO.toDTOList(customers);
        return ResponseEntity.ok(customerDTOList);
    }
}
