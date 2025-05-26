package com.petshop.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.petshop.dto.owner.OwnerDTO;
import com.petshop.petshop.dto.owner.OwnerRequest;
import com.petshop.petshop.dto.owner.OwnerResponse;
import com.petshop.petshop.dto.owner.OwnerWithAnimalsResponse;
import com.petshop.petshop.service.AnimalService;
import com.petshop.petshop.service.OwnerService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private AnimalService animalService;
 
    @GetMapping
    public List<OwnerResponse> getAllOwners() {
        return ownerService.getAllActiveOwners();
    }

    @GetMapping("/{cpf}")
    public OwnerWithAnimalsResponse getOwnerWithAnimals(@PathVariable @Valid String cpf) {
        return ownerService.getOwnerWithAnimalsByCpf(cpf);
    }

    @Transactional
    @PostMapping
    public OwnerDTO addOwner(@RequestBody @Valid OwnerRequest owner) {
        return ownerService.addOwner(owner);
    }

    @Transactional
    @PutMapping("/{cpf}")
    public OwnerDTO reactiveAllOwnerWithAnimals(@PathVariable @Valid String cpf) {
        return ownerService.reactiveOwnerWithAnimals(cpf);
    }

    @Transactional
    @DeleteMapping("/{cpf}")
    public void deleteOwnerByCpf(@PathVariable @Valid String cpf) {
        animalService.deleteAllOwnerAnimals(cpf);
        ownerService.deleteOwnerByCpf(cpf);
    }
}
