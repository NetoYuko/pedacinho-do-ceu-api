package com.backend.pedacinho_do_ceu_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AdocaoRequestDTO(
        @Schema(description = "ID do animal em processo de adoção")
        @NotNull(message = "O ID do animal é obrigatório")
        UUID animalId,

        @Schema(description = "ID do tutor interessado")
        @NotNull(message = "O ID do tutor é obrigatório")
        UUID tutorId,

        @Schema(description = "Status atual do processo", example = "ANALISE")
        @NotBlank(message = "O status do processo é obrigatório")
        String status
) {}