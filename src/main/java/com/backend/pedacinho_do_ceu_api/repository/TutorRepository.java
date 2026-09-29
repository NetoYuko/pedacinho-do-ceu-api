package com.backend.pedacinho_do_ceu_api.repository;

import com.backend.pedacinho_do_ceu_api.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, UUID> {
    Optional<Tutor> findByCpf(String cpf);
}
