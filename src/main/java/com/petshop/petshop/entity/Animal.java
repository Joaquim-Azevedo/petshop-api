package com.petshop.petshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_animal")
public class Animal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private String id;
    private String name;
    private boolean neutred;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Animal(String id, String name, boolean neutred, boolean active, Breed breed, Owner owner) {
        this.id = id;
        this.name = name;
        this.neutred = neutred;
        this.active = active;
        this.breed = breed;
        this.owner = owner;
    }

    public Animal() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public boolean isNeutred() {
        return neutred;
    }

    public void setNeutred(boolean neutred) {
        this.neutred = neutred;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
