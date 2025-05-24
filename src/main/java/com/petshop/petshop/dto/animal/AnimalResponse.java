package com.petshop.petshop.dto.animal;

public class AnimalResponse {
    
    private String id;
    private String name;
    private Boolean neutred;
    private AnimalTypeDTO type;


    public AnimalResponse(String id, String name, Boolean neutred, AnimalTypeDTO type) {
        this.id = id;
        this.name = name;
        this.neutred = neutred;
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

    public Boolean getNeutred() {
        return neutred;
    }

    public AnimalTypeDTO getType() {
        return type;
    }
}
