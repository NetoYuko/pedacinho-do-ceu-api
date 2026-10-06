package com.backend.pedacinho_do_ceu_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta contendo o token de acesso gerado após o login")
public record AuthResponseDTO(
        @Schema(description = "Token JWT para ser enviado na autorização das próximas requisições")
        String token
) {}
