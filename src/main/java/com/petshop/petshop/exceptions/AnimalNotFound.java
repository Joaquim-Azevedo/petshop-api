package com.petshop.petshop.exceptions;

public class AnimalNotFound extends RuntimeException{
    public AnimalNotFound(String message) {
        super(message);
    }
}
