package com.backend.pedacinho_do_ceu_api.controller;

import com.backend.pedacinho_do_ceu_api.dto.UsuarioAtualizarRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.pedacinho_do_ceu_api.dto.UsuarioRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Deve atualizar os dados do usuário e retornar HTTP 200")
    void deveAtualizarUsuario() throws Exception {
        UsuarioRequestDTO criacaoDto = new UsuarioRequestDTO("Marcos", "marcos@ong.com", "senha123", "VISUALIZADOR");
        String responseJson = mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(criacaoDto)))
                .andReturn().getResponse().getContentAsString();

        String idGerado = objectMapper.readTree(responseJson).get("id").asText();

        UsuarioAtualizarRequestDTO atualizacaoDto = new UsuarioAtualizarRequestDTO("Marcos Silva", null, "ADMIN");

        mockMvc.perform(put("/api/usuarios/" + idGerado)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(atualizacaoDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Marcos Silva"))
                .andExpect(jsonPath("$.perfil").value("ADMIN"));
    }
}
