package com.petshop.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("api/v1/dono")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private AnimalService animalService;
 
    @GetMapping
    public ResponseEntity<List<OwnerResponse>> getAllOwners() {
        var listResponse = ownerService.getAllActiveOwners();
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<OwnerWithAnimalsResponse> getOwnerWithAnimals(@PathVariable String cpf) {
        var response = ownerService.getOwnerWithAnimalsByCpf(cpf);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<OwnerDTO> addOwner(@RequestBody @Valid OwnerRequest owner) {
        var dto = ownerService.addOwner(owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Transactional
    @PutMapping("/{cpf}")
    public ResponseEntity<OwnerDTO> reactiveAllOwnerWithAnimals(@PathVariable String cpf) {
        var dto = ownerService.reactiveOwnerWithAnimals(cpf);
        return ResponseEntity.ok(dto);
    }

    @Transactional
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteOwnerByCpf(@PathVariable String cpf) {
        animalService.deleteAllOwnerAnimals(cpf);
        ownerService.deleteOwnerByCpf(cpf);
        return ResponseEntity.noContent().build();
    }
}
