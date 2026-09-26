package com.backend.pedacinho_do_ceu_api.repository;

import com.backend.pedacinho_do_ceu_api.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, UUID> {
}
