package com.petshop.petshop.dto.animal;

import com.petshop.petshop.entity.Breed;

public class BreedDTO {
    
    private String name;

    public BreedDTO(Breed breed) {
        name = breed.getName();
    }

    public BreedDTO(String name) {
        this.name = name;
    }

    public BreedDTO() {
        
    }

    public String getName() {
        return name;
    }
}
