package com.dohuy.jpapart2.request;

import lombok.Data;

@Data
public class StudentRequest {
    private Long id;
    private String name;
    private String email;
}
