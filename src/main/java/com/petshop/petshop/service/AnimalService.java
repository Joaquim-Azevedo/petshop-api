package com.petshop.petshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.petshop.dto.animal.AnimalDTO;
import com.petshop.petshop.dto.animal.AnimalRequest;
import com.petshop.petshop.dto.animal.AnimalResponse;
import com.petshop.petshop.entity.Animal;
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
        var owner = ownerRepository.findOwnerByCpf(request.getOwnerCpf());
        var breed = breedRepository.findByNormalizedName(request.getBreed());

        // Creating animal with the request manually
        Animal animal = new Animal(
                request.getId(), 
                request.getName(), 
                request.isCastrated(), 
                request.isActive(),
                breed, 
                owner);

        animalRepository.save(animal);

        return new AnimalDTO(animal);
    }

    // to implement
    public AnimalResponse getAnimalById(String id) {
        var optionalAnimal = animalRepository.findById(id);
        if (optionalAnimal.isPresent()) {
                Animal animal = optionalAnimal.get();
                return new AnimalResponse(animal);
        } else {
                throw new RuntimeException("Animal not found");
        }
    }

    public List<AnimalResponse> getAllActiveAnimals() {
        return animalRepository.findAllByActiveTrue().stream()
                .map(t -> new AnimalResponse(t))
                .toList();
    }

    public List<AnimalResponse> getAnimalsByOwnerCpf(String cpf) {
        return animalRepository.findAllByOwnerCpf(cpf).stream()
                .map(t -> new AnimalResponse(t))
                .toList();
    }

    // Update --> neutred
    public AnimalResponse castrateAnimalById(String id) {
        var optionalAnimal = animalRepository.findById(id);
        if (optionalAnimal.isPresent()) {
                Animal animal = optionalAnimal.get();
                animal.setCastrated(true);
                return new AnimalResponse(animal);
        } else {
                throw new RuntimeException("Animal not found or inexistent");
        }
    }

    public void deleteAnimalById(String id) {
        var optionalAnimal = animalRepository.findById(id);
        if (optionalAnimal.isPresent()) {
                Animal animal = optionalAnimal.get();
                animal.setActive(false);
        } else {
                throw new RuntimeException("Animal not found");
        }
    }

    public void deleteAllOwnerAnimals(String cpf) {
        animalRepository.findAllByOwnerCpf(cpf).stream()
                .forEach(t -> t.setActive(false));
    }

}
