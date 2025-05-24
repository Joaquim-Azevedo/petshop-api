package com.petshop.petshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.petshop.dto.animal.AnimalResponse;
import com.petshop.petshop.dto.animal.AnimalTypeDTO;
import com.petshop.petshop.dto.animal.BreedDTO;
import com.petshop.petshop.dto.owner.OwnerDTO;
import com.petshop.petshop.dto.owner.OwnerResponse;
import com.petshop.petshop.dto.owner.OwnerWithAnimalsResponse;
import com.petshop.petshop.entity.Animal;
import com.petshop.petshop.entity.Owner;
import com.petshop.petshop.repository.AnimalRepository;
import com.petshop.petshop.repository.AnimalTypeListRepository;
import com.petshop.petshop.repository.OwnerRepository;

@Service
public class OwnerService {
    
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalTypeListRepository listRepository;

    public OwnerDTO addOwner(OwnerDTO dto) {
        // To return a DTO and not a ENTITY "Owner"
        return new OwnerDTO(ownerRepository.save(new Owner(dto)));
    }

    public List<OwnerResponse> getAllOwners() {
        var result = ownerRepository.findAll();
        return result.stream().map(t -> new OwnerResponse(t))
                .toList();
    }

    public OwnerWithAnimalsResponse getOwnerWithAnimalsByCpf(String cpf) {
        var ownerResponse = new OwnerResponse(ownerRepository.findOwnerByCpf(cpf));
        
        List<Animal> animals = animalRepository.findAllByOwnerCpf(cpf);
        List<AnimalResponse> animalstoResponse = animals.stream()
                .map(t -> new AnimalResponse(
                            t.getId(),
                            t.getName(),
                            t.isNeutred(),
                            new AnimalTypeDTO(
                                    listRepository.findById(t.getBreed().getType().getId()).get(),
                                    new BreedDTO(t.getBreed())
                            )
                ))
                .toList();
        OwnerWithAnimalsResponse response = new OwnerWithAnimalsResponse(
                    ownerResponse,
                    animalstoResponse
            ); 
        return response;
    }

    public void deleteOwnerByCpf(String cpf) {
        var owner = ownerRepository.findOwnerByCpf(cpf);
        if(owner != null) {
            owner.setActive(false);
        } else {
            throw new RuntimeException("Owner not found");
        }
    }
}
