package com.petshop.petshop.dto.animal;

import com.petshop.petshop.entity.Animal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AnimalRequest {

    @NotBlank
    private String name;
    
    @NotNull
    private Boolean castrated;

    @NotBlank(message = "Breed can't be blank")
    private String breed;

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    private String ownerCpf;

    public AnimalRequest(Animal animal) {
        name = animal.getName();
        castrated = animal.isCastrated();
        ownerCpf = animal.getOwner().getCpf();
    }

    public AnimalRequest(String name, Boolean castrated, Boolean active, String breed,
            String ownerCpf) {
        this.name = name;
        this.castrated = castrated;
        this.breed = breed;
        this.ownerCpf = ownerCpf;
    }

    public AnimalRequest() {

    }

    public String getName() {
        return name;
    }

    public Boolean isCastrated() {
        return castrated;
    }

    public String getBreed() {
        return breed;
    }

    public String getOwnerCpf() {
        return ownerCpf;
    }
}
