package com.petshop.petshop.entity;

import com.petshop.petshop.dto.AnimalTypeDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_animaltype_list")
public class AnimalTypeList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    public AnimalTypeList(AnimalTypeDTO dto) {
        type = dto.getName();
    }

    public AnimalTypeList(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public AnimalTypeList() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
