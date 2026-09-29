package com.backend.pedacinho_do_ceu_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

public record UsuarioAtualizarRequestDTO(
        @Schema(description = "Novo nome do usuário", example = "Ana Voluntária")
        String nome,

        @Schema(description = "Novo e-mail de acesso", example = "ana_novo@ong.com.br")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @Schema(description = "Novo perfil de acesso", example = "ADMIN")
        String perfil
) {}
