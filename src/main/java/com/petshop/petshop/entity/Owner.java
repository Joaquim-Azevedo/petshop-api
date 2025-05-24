package com.petshop.petshop.entity;

import com.petshop.petshop.dto.OwnerDTO;
import com.petshop.petshop.dto.OwnerResponse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_owner")
public class Owner {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(length = 11)
    private String cpf;

    @Column(length = 11)
    private String phone;
    private String email;
    private Boolean active;

    public Owner(OwnerDTO dto) {
        name = dto.getName();
        cpf = dto.getCpf();
        phone = dto.getPhone();
        email = dto.getEmail();
        this.active = true;
    }

    public Owner(OwnerResponse response) {
        id = response.getId();
        name = response.getName();
        cpf = response.getCpf();
    }
    
    public Owner(Long id, String name, String cpf, String phone, String email, Boolean active) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
        this.active = active;
    }

    public Owner() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
