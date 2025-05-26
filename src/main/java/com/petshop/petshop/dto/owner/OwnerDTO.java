package com.petshop.petshop.dto.owner;

import com.petshop.petshop.entity.Owner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class OwnerDTO {
    
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    private String cpf;

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    private String phone;

    @NotBlank
    private String email;

    private Boolean active;

    public OwnerDTO(Owner owner) {
        id = owner.getId();
        name = owner.getName();
        cpf = owner.getCpf();
        phone = owner.getPhone();
        email = owner.getEmail();
        active = owner.getActive();
    }

    public OwnerDTO(Long id, @NotBlank String name, @NotBlank String cpf, @NotBlank String phone,
            @NotBlank String email, Boolean active) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
        this.active = active;
    }

    public OwnerDTO() {

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

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getActive() {
        return active;
    }
}
