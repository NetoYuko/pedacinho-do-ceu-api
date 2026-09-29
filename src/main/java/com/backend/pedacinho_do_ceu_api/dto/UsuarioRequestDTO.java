package com.backend.pedacinho_do_ceu_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @Schema(description = "Nome do usuário", example = "Ana Voluntária")
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @Schema(description = "E-mail de acesso", example = "ana@ong.com.br")
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @Schema(description = "Senha de acesso", example = "SenhaSegura123")
        @NotBlank(message = "A senha é obrigatória")
        String senha,

        @Schema(description = "Perfil de acesso", example = "VISUALIZADOR")
        @NotBlank(message = "O perfil é obrigatório")
        String perfil
) {}
