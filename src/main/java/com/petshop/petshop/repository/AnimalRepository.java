package com.petshop.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petshop.petshop.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, String>{
    
    List<Animal> findAllByOwnerCpf(String cpf);
    List<Animal> findAllByActiveTrue();

}
