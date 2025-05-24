package com.petshop.petshop.entity;

import com.petshop.petshop.dto.BreedDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_breeds")
public class Breed {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "normalized_name")
    private String normalized_name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private AnimalTypeList type;

    public Breed(BreedDTO dto) {
        name = dto.getName();
    }

    public Breed(Long id, String name, String normalized_name, AnimalTypeList type) {
        this.id = id;
        this.name = name;
        this.normalized_name = normalized_name;
        this.type = type;
    }

    public Breed() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnimalTypeList getType() {
        return type;
    }
    
    public void setType(AnimalTypeList type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNormalized_name() {
        return normalized_name;
    }

    public void setNormalized_name(String normalized_name) {
        this.normalized_name = normalized_name;
    }


}
