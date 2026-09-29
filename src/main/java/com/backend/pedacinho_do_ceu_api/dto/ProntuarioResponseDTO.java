package com.backend.pedacinho_do_ceu_api.dto;

import com.backend.pedacinho_do_ceu_api.model.ProntuarioMedicamento;

import java.time.LocalDate;
import java.util.UUID;

public record ProntuarioResponseDTO(
        UUID id,
        UUID animalId,
        String nomeMedicamento,
        String tipo,
        LocalDate dataAplicacao,
        LocalDate dataProximaDose,
        String observacoes
) {
    public ProntuarioResponseDTO(ProntuarioMedicamento p) {
        this(
                p.getId(),
                p.getAnimal().getId(),
                p.getNomeMedicamento(),
                p.getTipo(),
                p.getDataAplicacao(),
                p.getDataProximaDose(),
                p.getObservacoes()
        );
    }
}
