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
    public List<AnimalResponse> getAllActiveAnimals() {
        return animalService.getAllActiveAnimals();
    }

    @GetMapping("/{cpf}")
    public List<AnimalResponse> getAnimalsByOwnerCpf(@PathVariable String cpf) {
        return animalService.getAnimalsByOwnerCpf(cpf);
    }

    @GetMapping("/pet/{id}")
    public AnimalResponse getAnimalById(@PathVariable String id) {
        return animalService.getAnimalById(id);
    }
    
    @Transactional
    @PostMapping
    public AnimalDTO addAnimal(@RequestBody @Valid AnimalRequest request) {
        return animalService.addAnimal(request);
    }
    
    @Transactional
    @PutMapping("/{id}")
    public AnimalDTO reactiveAnimalById(@PathVariable String id) {
        return animalService.reactiveAnimalById(id);
    }

    @Transactional
    @PutMapping("/castrate/{id}")
    public AnimalResponse castrateAnimalById(@PathVariable String id) {
        return animalService.castrateAnimalById(id);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteAnimalById(@PathVariable @Valid String id) {
        animalService.deleteAnimalById(id);
    }
}
