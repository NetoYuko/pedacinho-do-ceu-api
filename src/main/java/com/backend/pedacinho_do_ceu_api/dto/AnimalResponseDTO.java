package com.backend.pedacinho_do_ceu_api.dto;

import com.backend.pedacinho_do_ceu_api.model.Animal;
import java.time.LocalDate;
import java.util.UUID;

public record AnimalResponseDTO(
        UUID id,
        String nome,
        String especie,
        Integer idadeMeses,
        String status,
        String fotoUrl,
        LocalDate dataCadastro
) {
    public AnimalResponseDTO(Animal animal) {
        this(
                animal.getId(),
                animal.getNome(),
                animal.getEspecie(),
                animal.getIdadeMeses(),
                animal.getStatus(),
                animal.getFotoUrl(),
                animal.getDataCadastro()
        );
    }
}
