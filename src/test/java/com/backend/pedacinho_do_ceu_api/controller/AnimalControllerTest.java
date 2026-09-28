package com.backend.pedacinho_do_ceu_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.pedacinho_do_ceu_api.dto.AnimalRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    @DisplayName("Deve cadastrar um animal com sucesso e retornar HTTP 201")
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
    @DisplayName("Deve listar animais com sucesso e retornar HTTP 200")
    void deveListarAnimais() throws Exception {
        mockMvc.perform(get("/api/animais"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
