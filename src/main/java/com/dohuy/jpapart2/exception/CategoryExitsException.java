package com.dohuy.jpapart2.exception;

public class CategoryExitsException extends RuntimeException {
    public CategoryExitsException(String message) {
        super(message);
    }
}
