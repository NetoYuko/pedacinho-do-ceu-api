package com.backend.pedacinho_do_ceu_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnimalRequestDTO(
        @Schema(description = "Nome do animal", example = "Caramelo")
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @Schema(description = "Espécie do animal", example = "CACHORRO")
        @NotBlank(message = "A espécie é obrigatória")
        String especie,

        @Schema(description = "Idade aproximada em meses", example = "24")
        Integer idadeMeses,

        @Schema(description = "Status atual de adoção", example = "DISPONIVEL")
        @NotBlank(message = "O status é obrigatório")
        String status
) {}
