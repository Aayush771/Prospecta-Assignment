package com.example.question1.Exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message) {
        super(message);
    }
    
}
