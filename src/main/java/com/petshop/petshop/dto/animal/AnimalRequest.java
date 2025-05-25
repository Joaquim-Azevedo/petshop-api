package com.petshop.petshop.dto.animal;

import com.petshop.petshop.entity.Animal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AnimalRequest {
    
    private String id;

    @NotBlank
    private String name;
    
    @NotNull
    private Boolean castrated;

    private Boolean active = true;

    @NotBlank(message = "Breed can't be blank")
    private String breed;

    @NotBlank
    @Size(max = 11)
    private String ownerCpf;

    public AnimalRequest(Animal animal) {
        name = animal.getName();
        castrated = animal.isCastrated();
        active = animal.isActive();
        ownerCpf = animal.getOwner().getCpf();
    }

    public AnimalRequest(String id, String name, Boolean castrated, Boolean active, String breed,
            String ownerCpf) {
        this.id = id;
        this.name = name;
        this.castrated = castrated;
        this.active = true;
        this.breed = breed;
        this.ownerCpf = ownerCpf;
    }

    public AnimalRequest() {

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

    public String getBreed() {
        return breed;
    }

    public String getOwnerCpf() {
        return ownerCpf;
    }
}
