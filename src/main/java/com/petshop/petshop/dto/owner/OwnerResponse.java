package com.petshop.petshop.dto.owner;

import com.petshop.petshop.entity.Owner;

import jakarta.validation.constraints.NotBlank;

public class OwnerResponse {
    
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String cpf;

    public OwnerResponse(Owner owner) {
        id = owner.getId();
        name = owner.getName();
        cpf = owner.getCpf();
    }

    public OwnerResponse(Long id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

    public OwnerResponse () {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }
}
