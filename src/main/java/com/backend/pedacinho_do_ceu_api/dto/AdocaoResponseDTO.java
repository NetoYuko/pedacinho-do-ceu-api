package com.backend.pedacinho_do_ceu_api.dto;

import com.backend.pedacinho_do_ceu_api.model.Adocao;

import java.time.LocalDate;
import java.util.UUID;

public record AdocaoResponseDTO(
        UUID id,
        UUID animalId,
        UUID tutorId,
        String status,
        LocalDate dataInicio,
        LocalDate dataConclusao
) {
    public AdocaoResponseDTO(Adocao adocao) {
        this(
                adocao.getId(),
                adocao.getAnimal().getId(),
                adocao.getTutor().getId(),
                adocao.getStatus(),
                adocao.getDataInicio(),
                adocao.getDataConclusao()
        );
    }
}
