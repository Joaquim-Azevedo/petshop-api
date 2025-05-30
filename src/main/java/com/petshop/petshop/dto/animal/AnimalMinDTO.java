package com.petshop.petshop.dto.animal;

import com.petshop.petshop.entity.Animal;

public class AnimalMinDTO {
 
    private String name;
    private Boolean castrated;
    private AnimalTypeDTO type;

    public AnimalMinDTO (Animal animal) {
        name = animal.getName();
        castrated = animal.isCastrated();
        type = new AnimalTypeDTO(
            animal.getBreed().getType(), 
            new BreedDTO(animal.getBreed())
        );
    }

    public AnimalMinDTO(String name, Boolean castrated, AnimalTypeDTO type) {
        this.name = name;
        this.castrated = castrated;
        this.type = type;
    }

    public AnimalMinDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCastrated() {
        return castrated;
    }

    public void setCastrated(Boolean castrated) {
        this.castrated = castrated;
    }

    public AnimalTypeDTO getType() {
        return type;
    }

    public void setType(AnimalTypeDTO type) {
        this.type = type;
    }
}
