package com.petshop.petshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.petshop.dto.animal.AnimalDTO;
import com.petshop.petshop.dto.animal.AnimalRequest;
import com.petshop.petshop.dto.animal.AnimalResponse;
import com.petshop.petshop.entity.Animal;
import com.petshop.petshop.exceptions.AnimalNotFound;
import com.petshop.petshop.exceptions.InvalidArgumentException;
import com.petshop.petshop.exceptions.OwnerNotFound;
import com.petshop.petshop.repository.AnimalRepository;
import com.petshop.petshop.repository.BreedRepository;
import com.petshop.petshop.repository.OwnerRepository;

@Service
public class AnimalService {
    
    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private BreedRepository breedRepository;
 
    public AnimalDTO addAnimal(AnimalRequest request) {
        String cpf = request.getOwnerCpf();
        var owner = ownerRepository.findOwnerByCpf(cpf)
                .orElseThrow(
                    () -> new OwnerNotFound("Dono/cliente com cpf '" + cpf + "' não encontrado"));
        var breed = breedRepository.findByNormalizedName(request.getBreed());

        // Creating animal with the request manually
        Animal animal = new Animal(
                null, 
                request.getName(), 
                request.isCastrated(), 
                true,
                breed, 
                owner);

        animalRepository.save(animal);

        return new AnimalDTO(animal);

    }

    // to implement
    public AnimalResponse getAnimalById(String id) {
        if(id == null || id.length() != 36) {
            throw new InvalidArgumentException("CPF inválido: deve conter 11 dígitos numéricos");
        }
        var animal = animalRepository.findById(id)
                        .orElseThrow(
                                () -> new AnimalNotFound("Animal com ID '" + id + "' não encontrado"));
        return new AnimalResponse(animal);
    }

    public List<AnimalResponse> getAllActiveAnimals() {
        return animalRepository.findAllByActiveTrue().stream()
                .map(AnimalResponse::new)
                .toList();
    }

    public List<AnimalResponse> getAnimalsByOwnerCpf(String cpf) {
        if(cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            throw new InvalidArgumentException("CPF inválido: deve conter 11 dígitos numéricos");
        }
        return animalRepository.findAllByOwnerCpf(cpf).stream()
                .map(AnimalResponse::new)
                .toList();
    }

    // Update --> neutred
    public AnimalResponse castrateAnimalById(String id) {
        var animal = animalRepository.findById(id)
                .orElseThrow(
                    () -> new AnimalNotFound("Animal com ID '" + id + "' não encontrado"));
                    
        animal.setCastrated(true);
        return new AnimalResponse(animal);
    }

    public AnimalDTO reactiveAnimalById(String id) {
        var animal = animalRepository.findById(id)
                .orElseThrow(
                    () -> new AnimalNotFound("Animal com ID '" + id + "' não encontrado"));

        animal.setActive(true);
        return new AnimalDTO(animal);

    }

    public void deleteAnimalById(String id) {
        var animal = animalRepository.findById(id)
                .orElseThrow(
                    () -> new AnimalNotFound("Animal com ID '" + id + "' não encontrado"));

        animal.setActive(false);

    }

    public void deleteAllOwnerAnimals(String cpf) {
        if(cpf == null || cpf.length() != 11 || cpf.matches("\\d+")) {
            throw new InvalidArgumentException("CPF inválido: deve conter 11 dígitos numéricos");
        }
        animalRepository.findAllByOwnerCpf(cpf).stream()
                .forEach(t -> t.setActive(false));
    }

}
