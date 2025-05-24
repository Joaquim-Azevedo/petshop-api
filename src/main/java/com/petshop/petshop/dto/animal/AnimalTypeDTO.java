package com.petshop.petshop.dto.animal;

import com.petshop.petshop.entity.AnimalTypeList;

import jakarta.validation.constraints.NotNull;

public class AnimalTypeDTO {
    
    // Change "name" to "type"

    @NotNull
    private String name;
    private BreedDTO breed;

    public AnimalTypeDTO(AnimalTypeList typeList, BreedDTO breed) {
        name = typeList.getType();
        this.breed = breed;
    }

    public AnimalTypeDTO(Long id, String name, BreedDTO breed) {
        this.name = name;
        this.breed = breed;
    }

    public AnimalTypeDTO() {

    }

    public String getName() {
        return name;
    }

    public BreedDTO getBreed() {
        return breed;
    }
}
