package com.backend.pedacinho_do_ceu_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.pedacinho_do_ceu_api.dto.AnimalRequestDTO;
import com.backend.pedacinho_do_ceu_api.dto.ProntuarioRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProntuarioMedicamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Test
    @DisplayName("Deve registrar um medicamento para um animal existente com sucesso")
    void deveRegistrarMedicamento() throws Exception {
        AnimalRequestDTO animalDto = new AnimalRequestDTO("Rex", "CACHORRO", 24, "DISPONIVEL");
        String animalResponse = mockMvc.perform(post("/api/animais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(animalDto)))
                .andReturn().getResponse().getContentAsString();

        String animalIdStr = objectMapper.readTree(animalResponse).get("id").asText();
        UUID animalId = UUID.fromString(animalIdStr);

        ProntuarioRequestDTO prontuarioDto = new ProntuarioRequestDTO(
                animalId,
                "Bravecto",
                "ANTIPULGAS",
                LocalDate.now(),
                LocalDate.now().plusMonths(3),
                "Sem reações adversas"
        );

        mockMvc.perform(post("/api/prontuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prontuarioDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.animalId").value(animalIdStr))
                .andExpect(jsonPath("$.nomeMedicamento").value("Bravecto"));
    }
}
