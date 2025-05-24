package com.petshop.petshop.dto;

import com.petshop.petshop.entity.Animal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AnimalDTO {
    
    private String id;

    @NotBlank
    private String name;
    
    @NotNull
    private Boolean neutred;

    private Boolean active = true;

    @NotBlank(message = "Breed can't be blank")
    private String breed;

    @NotBlank
    @Size(max = 11)
    private String ownerCpf;

    public AnimalDTO(Animal animal) {
        name = animal.getName();
        neutred = animal.isNeutred();
        active = animal.isActive();
        ownerCpf = animal.getOwner().getCpf();
    }

    public AnimalDTO(String id, String name, Boolean neutred, Boolean active, String breed,
            String ownerCpf) {
        this.id = id;
        this.name = name;
        this.neutred = neutred;
        this.active = true;
        this.breed = breed;
        this.ownerCpf = ownerCpf;
    }

    public AnimalDTO() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getNeutred() {
        return neutred;
    }

    public Boolean getActive() {
        return active;
    }

    public String getBreed() {
        return breed;
    }

    public String getOwnerCpf() {
        return ownerCpf;
    }
}
