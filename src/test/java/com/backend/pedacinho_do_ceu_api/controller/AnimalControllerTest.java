package com.backend.pedacinho_do_ceu_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.pedacinho_do_ceu_api.dto.AnimalRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AnimalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Deve cadastrar um animal com sucesso")
    @WithMockUser(roles = "ADMIN")
    void deveCadastrarAnimal() throws Exception {
        AnimalRequestDTO dto = new AnimalRequestDTO("Bolinha", "GATO", 12, "DISPONIVEL");

        mockMvc.perform(post("/api/animais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Bolinha"));
    }

    @Test
    @DisplayName("Deve listar animais com sucesso")
    @WithMockUser(roles = "USER")
    void deveListarAnimais() throws Exception {
        mockMvc.perform(get("/api/animais"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Deve atualizar um animal com sucesso")
    @WithMockUser(roles = "ADMIN")
    void deveAtualizarAnimal() throws Exception {
        AnimalRequestDTO criacaoDto = new AnimalRequestDTO("Rex", "CACHORRO", 5, "DISPONIVEL");
        String responseJson = mockMvc.perform(post("/api/animais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(criacaoDto)))
                .andReturn().getResponse().getContentAsString();

        String idGerado = objectMapper.readTree(responseJson).get("id").asText();

        AnimalRequestDTO atualizacaoDto = new AnimalRequestDTO("Rex Editado", "CACHORRO", 6, "DISPONIVEL");

        mockMvc.perform(put("/api/animais/" + idGerado)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(atualizacaoDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Rex Editado"))
                .andExpect(jsonPath("$.idadeMeses").value(6));
    }

    @Test
    @DisplayName("Deve excluir um animal com sucesso")
    @WithMockUser(roles = "ADMIN")
    void deveExcluirAnimal() throws Exception {
        AnimalRequestDTO criacaoDto = new AnimalRequestDTO("Thor", "CACHORRO", 24, "DISPONIVEL");
        String responseJson = mockMvc.perform(post("/api/animais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(criacaoDto)))
                .andReturn().getResponse().getContentAsString();

        String idGerado = objectMapper.readTree(responseJson).get("id").asText();

        mockMvc.perform(delete("/api/animais/" + idGerado))
                .andExpect(status().isNoContent());
    }
}
