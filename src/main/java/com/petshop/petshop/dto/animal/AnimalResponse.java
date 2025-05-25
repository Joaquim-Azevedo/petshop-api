package com.petshop.petshop.dto.animal;

import com.petshop.petshop.entity.Animal;

public class AnimalResponse {
    
    private String id;
    private String name;
    private String ownerCpf;
    private Boolean castrated;
    private AnimalTypeDTO type;

    public AnimalResponse(Animal animal) {
        this.id = animal.getId();
        this.name = animal.getName();
        this.ownerCpf = animal.getOwner().getCpf();
        this.castrated = animal.isCastrated();
        this.type = new AnimalTypeDTO(animal.getBreed().getType(), 
                new BreedDTO(animal.getBreed()));
    }

    public AnimalResponse(String id, String name, String ownerCpf, Boolean castrated, AnimalTypeDTO type) {
        this.id = id;
        this.name = name;
        this.ownerCpf = ownerCpf;
        this.castrated = castrated;
        this.type = type;
    }

    public AnimalResponse() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwnerCpf() {
        return ownerCpf;
    }

    public Boolean isCastrated() {
        return castrated;
    }

    public AnimalTypeDTO getType() {
        return type;
    }
}
