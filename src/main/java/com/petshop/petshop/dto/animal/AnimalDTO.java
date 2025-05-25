package com.petshop.petshop.dto.animal;

import com.petshop.petshop.dto.owner.OwnerResponse;
import com.petshop.petshop.entity.Animal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AnimalDTO {
    
    private String id;

    @NotBlank
    private String name;
    
    @NotNull
    private Boolean castrated;
    private Boolean active;
    private AnimalTypeDTO type;

    private OwnerResponse owner;

    public AnimalDTO(Animal animal) {
        id = animal.getId();
        name = animal.getName();
        castrated = animal.isCastrated();
        active = animal.isActive();
        type = new AnimalTypeDTO(animal.getBreed().getType(),
                new BreedDTO(animal.getBreed()));
        owner = new OwnerResponse(animal.getOwner());
    }

    public AnimalDTO(String id, @NotBlank String name, @NotNull Boolean castrated, Boolean active, AnimalTypeDTO type,
            OwnerResponse owner) {
        this.id = id;
        this.name = name;
        this.castrated = castrated;
        this.active = active;
        this.type = type;
        this.owner = owner;
    }

    public AnimalDTO() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean isCastrated() {
        return castrated;
    }

    public Boolean isActive() {
        return active;
    }

    public AnimalTypeDTO getType() {
        return type;
    }

    public OwnerResponse getOwner() {
        return owner;
    }
}