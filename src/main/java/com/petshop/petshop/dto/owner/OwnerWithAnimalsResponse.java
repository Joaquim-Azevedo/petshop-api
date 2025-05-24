package com.petshop.petshop.dto.owner;

import java.util.List;

import com.petshop.petshop.dto.animal.AnimalResponse;

public class OwnerWithAnimalsResponse {
    
    OwnerResponse owner;
    List<AnimalResponse> animals;

    public OwnerWithAnimalsResponse(OwnerResponse owner, List<AnimalResponse> animals) {
        this.owner = owner;
        this.animals = animals;
    }

    public OwnerWithAnimalsResponse() {

    }

    public List<AnimalResponse> getAnimals() {
        return animals;
    }

    public OwnerResponse getOwner() {
        return owner;
    }
}
