package com.petshop.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.petshop.dto.animal.AnimalDTO;
import com.petshop.petshop.dto.animal.AnimalRequest;
import com.petshop.petshop.service.AnimalService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pet")
public class AnimalController {
    
    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<AnimalDTO> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/{cpf}")
    public List<AnimalDTO> getAnimalsByOwnerCpf(@PathVariable @Valid String cpf) {
        return animalService.getAnimalsByOwnerCpf(cpf);
    }

    @Transactional
    @PostMapping
    public AnimalRequest addAnimal(@RequestBody @Valid AnimalDTO dto) {
        return animalService.addAnimal(dto);
    }
}
