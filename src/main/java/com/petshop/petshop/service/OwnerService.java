package com.petshop.petshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.petshop.dto.animal.AnimalResponse;
import com.petshop.petshop.dto.owner.OwnerDTO;
import com.petshop.petshop.dto.owner.OwnerRequest;
import com.petshop.petshop.dto.owner.OwnerResponse;
import com.petshop.petshop.dto.owner.OwnerWithAnimalsResponse;
import com.petshop.petshop.entity.Animal;
import com.petshop.petshop.entity.Owner;
import com.petshop.petshop.repository.AnimalRepository;
import com.petshop.petshop.repository.OwnerRepository;

@Service
public class OwnerService {
    
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public OwnerDTO addOwner(OwnerRequest request) {
        // To return a DTO and not a ENTITY "Owner"
        if (!ownerRepository.existsByCpf(request.getCpf())) {
            return new OwnerDTO(ownerRepository.save(new Owner(request)));
        } else {
            throw new RuntimeException("Owner with this CPF already exists");
        }
    }

    public List<OwnerResponse> getAllActiveOwners() {
        // Mapping all owners to owner 'response' 
        List<Owner> result = ownerRepository.findAllByActiveTrue();
        return result.stream().map(t -> new OwnerResponse(t))
                .toList();
    }

    public OwnerWithAnimalsResponse getOwnerWithAnimalsByCpf(String cpf) {
        OwnerDTO ownerDTO = new OwnerDTO(ownerRepository.findOwnerByCpf(cpf));
        List<Animal> animals = animalRepository.findAllByOwnerCpf(cpf);
        
        // mapping animals to animal 'response'
        List<AnimalResponse> animalstoResponse = animals.stream()
                .map(t -> new AnimalResponse(t))
                .toList();
        OwnerWithAnimalsResponse response = new OwnerWithAnimalsResponse(
                    ownerDTO,
                    animalstoResponse
            ); 
        return response;
    }

    public OwnerDTO reactiveOwnerWithAnimals(String cpf) {
        Owner owner = ownerRepository.findOwnerByCpf(cpf);
        
        if (owner != null) {
            owner.setActive(true);
            var ownerDTO = new OwnerDTO(owner);
            return ownerDTO;
        } else {
            throw new RuntimeException("Owner not found");
        }
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
