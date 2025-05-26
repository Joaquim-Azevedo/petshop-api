package com.petshop.petshop.dto.owner;

import com.petshop.petshop.entity.Owner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class OwnerRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NotBlank
    @Size(max = 11)
    private String phone;

    @NotBlank
    private String email;

    private Boolean active;

    public OwnerRequest(Owner owner) {
        name = owner.getName();
        cpf = owner.getCpf();
        phone = owner.getPhone();
        email = owner.getEmail();
        active = owner.getActive();
    }

    public OwnerRequest(@NotBlank String name, @NotBlank String cpf, @NotBlank String phone,
            @NotBlank String email, Boolean active) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
        this.active = active;
    }

    public OwnerRequest() {

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
