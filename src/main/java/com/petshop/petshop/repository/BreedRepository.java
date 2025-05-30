package com.petshop.petshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petshop.petshop.entity.Breed;

public interface BreedRepository extends JpaRepository<Breed, Long>{
    
    Optional<Breed> findByName(String breed);

    @Query("SELECT b FROM Breed b WHERE b.normalized_name LIKE %:breed%")
    Optional<Breed> findByNormalizedName(@Param("breed") String breed);

}
