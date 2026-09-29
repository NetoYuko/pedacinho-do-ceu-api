package com.backend.pedacinho_do_ceu_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ProntuarioRequestDTO(
        @Schema(description = "ID do animal que receberá o medicamento")
        @NotNull(message = "O ID do animal é obrigatório")
        UUID animalId,

        @Schema(description = "Nome do medicamento", example = "Bravecto")
        @NotBlank(message = "O nome do medicamento é obrigatório")
        String nomeMedicamento,

        @Schema(description = "Tipo do medicamento", example = "VERMIFUGO")
        @NotBlank(message = "O tipo de medicamento é obrigatório")
        String tipo,

        @Schema(description = "Data da aplicação")
        @NotNull(message = "A data de aplicação é obrigatória")
        LocalDate dataAplicacao,

        @Schema(description = "Data da próxima dose (opcional)")
        LocalDate dataProximaDose,

        @Schema(description = "Observações gerais", example = "Animal apresentou leve sonolência")
        String observacoes
) {}
