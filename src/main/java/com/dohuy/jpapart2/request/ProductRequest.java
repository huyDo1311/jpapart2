package com.dohuy.jpapart2.request;

import lombok.Data;

@Data
public class ProductRequest {
    private Long id;

    private String name;

    private Double price;

    private String description;

}
