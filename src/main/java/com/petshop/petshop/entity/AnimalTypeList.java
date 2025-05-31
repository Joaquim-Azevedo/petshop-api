package com.petshop.petshop.entity;

import com.petshop.petshop.dto.animal.AnimalTypeDTO;

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
    private String name;

    public AnimalTypeList(AnimalTypeDTO dto) {
        name = dto.getName();
    }

    public AnimalTypeList(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AnimalTypeList() {

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
}
