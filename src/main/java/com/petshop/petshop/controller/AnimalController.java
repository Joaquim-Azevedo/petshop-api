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

import com.petshop.petshop.dto.animal.AnimalDTO;
import com.petshop.petshop.dto.animal.AnimalRequest;
import com.petshop.petshop.dto.animal.AnimalResponse;
import com.petshop.petshop.service.AnimalService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/petshop")
public class AnimalController {
    
    @Autowired
    private AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<AnimalResponse>> getAllActiveAnimals() {
        var listResponse = animalService.getAllActiveAnimals();
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<List<AnimalResponse>> getAnimalsByOwnerCpf(@PathVariable String cpf) {
        var listResponse = animalService.getAnimalsByOwnerCpf(cpf);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/pet/{id}")
    public ResponseEntity<AnimalResponse> getAnimalById(@PathVariable String id) {
        var response = animalService.getAnimalById(id);
        return ResponseEntity.ok(response);
    }
    
    @Transactional
    @PostMapping
    public ResponseEntity<AnimalDTO> addAnimal(@RequestBody @Valid AnimalRequest request) {
        var dto = animalService.addAnimal(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dto);
    }
    
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<AnimalDTO> reactiveAnimalById(@PathVariable String id) {
        var dto = animalService.reactiveAnimalById(id);
        return ResponseEntity.ok(dto);
    }

    @Transactional
    @PutMapping("/castrate/{id}")
    public ResponseEntity<AnimalResponse> castrateAnimalById(@PathVariable String id) {
        var response = animalService.castrateAnimalById(id);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimalById(@PathVariable @Valid String id) {
        animalService.deleteAnimalById(id);
        return ResponseEntity.noContent().build();
    }
}
