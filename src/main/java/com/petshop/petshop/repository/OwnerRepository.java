package com.petshop.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petshop.petshop.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long>{
    
    Owner findOwnerByCpf(String cpf);
    List<Owner> findAllByActiveTrue();
    Boolean existsByCpf(String cpf);
}
