package com.petshop.petshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.petshop.dto.animal.AnimalDTO;
import com.petshop.petshop.dto.animal.AnimalRequest;
import com.petshop.petshop.dto.animal.AnimalTypeDTO;
import com.petshop.petshop.dto.animal.BreedDTO;
import com.petshop.petshop.dto.owner.OwnerResponse;
import com.petshop.petshop.entity.Animal;
import com.petshop.petshop.repository.AnimalRepository;
import com.petshop.petshop.repository.AnimalTypeListRepository;
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

    @Autowired
    private AnimalTypeListRepository listRepository;

 
    public AnimalRequest addAnimal(AnimalDTO dto) {
        var owner = ownerRepository.findOwnerByCpf(dto.getOwnerCpf());
        var breed = breedRepository.findByNormalizedName(dto.getBreed());

        var animalType = new AnimalTypeDTO(listRepository
            .findById(breed.getType().getId()).get(), new BreedDTO(breed));

        // Creating animal with dto getters manually
        var animal = new Animal(
                dto.getId(), 
                dto.getName(), 
                dto.getNeutred(), 
                dto.getActive(),
                breed, 
                owner);
        animalRepository.save(animal);

        return new AnimalRequest(
            animal.getId(),
            animal.getName(),
            animal.isNeutred(),
            animal.isActive(),
            animalType,
            new OwnerResponse(owner)
        );
    }

    public AnimalDTO getAnimalById(String id) {
        var optionalAnimal = animalRepository.findById(id);
        if (optionalAnimal.isPresent()) {
                var animal = optionalAnimal.get();
                return new AnimalDTO(animal);
        } else {
                throw new RuntimeException("Animal not found");
        }
    }

    public List<AnimalDTO> getAllAnimals() {
        return animalRepository.findAll().stream()
                .map(t -> new AnimalDTO(
                    t.getId(),
                    t.getName(),
                    t.isNeutred(),
                    t.isActive(),
                    t.getBreed().getName(),
                    t.getOwner().getCpf()))
                .toList();
                
    }

    public List<AnimalDTO> getAnimalsByOwnerCpf(String cpf) {
        return animalRepository.findAllByOwnerCpf(cpf).stream()
                .map(t -> new AnimalDTO(t))
                .toList();
    }

    // Update

    public void deleteAnimal(String id) {
        var optionalAnimal = animalRepository.findById(id);
        if (optionalAnimal.isPresent()) {
                var animal = optionalAnimal.get();
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
