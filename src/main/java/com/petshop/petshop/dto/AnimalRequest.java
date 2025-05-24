package com.petshop.petshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AnimalRequest {
    
    private String id;

    @NotBlank
    private String name;
    
    @NotNull
    private Boolean neutred;
    private Boolean active;
    private AnimalTypeDTO type;

    private OwnerResponse owner;

    public AnimalRequest(String id, @NotBlank String name, @NotNull Boolean neutred, Boolean active, AnimalTypeDTO type,
            OwnerResponse owner) {
        this.id = id;
        this.name = name;
        this.neutred = neutred;
        this.active = active;
        this.type = type;
        this.owner = owner;
    }

    public AnimalRequest() {
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

    public AnimalTypeDTO getType() {
        return type;
    }

    public OwnerResponse getOwner() {
        return owner;
    }
}
