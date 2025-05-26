package com.petshop.petshop.exceptions;

public class OwnerNotFound extends RuntimeException{
    public OwnerNotFound(String message) {
        super(message);
    }
}
