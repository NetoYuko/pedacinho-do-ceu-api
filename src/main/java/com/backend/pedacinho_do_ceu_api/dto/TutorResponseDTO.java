package com.backend.pedacinho_do_ceu_api.dto;

import com.backend.pedacinho_do_ceu_api.model.Tutor;
import java.util.UUID;

public record TutorResponseDTO(
        UUID id,
        String nome,
        String cpf,
        String telefone,
        String endereco
) {
    public TutorResponseDTO(Tutor tutor) {
        this(tutor.getId(), tutor.getNome(), tutor.getCpf(), tutor.getTelefone(), tutor.getEndereco());
    }
}
