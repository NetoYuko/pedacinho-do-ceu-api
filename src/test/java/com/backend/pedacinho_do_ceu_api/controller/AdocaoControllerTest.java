package com.backend.pedacinho_do_ceu_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.pedacinho_do_ceu_api.dto.AdocaoRequestDTO;
import com.backend.pedacinho_do_ceu_api.dto.AnimalRequestDTO;
import com.backend.pedacinho_do_ceu_api.dto.TutorRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AdocaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Test
    @DisplayName("Deve aprovar uma adoção e atualizar o status do animal para ADOTADO automaticamente")
    void deveRegistrarAprovacaoEAtualizarAnimal() throws Exception {
        AnimalRequestDTO animalDto = new AnimalRequestDTO("Bolinha", "GATO", 5, "DISPONIVEL");
        String animalResponse = mockMvc.perform(post("/api/animais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(animalDto)))
                .andReturn().getResponse().getContentAsString();
        String animalIdStr = objectMapper.readTree(animalResponse).get("id").asText();
        UUID animalId = UUID.fromString(animalIdStr);

        TutorRequestDTO tutorDto = new TutorRequestDTO("João Silva", "555.666.777-88", "(11) 98888-7777", "Rua do Adotante, 45");
        String tutorResponse = mockMvc.perform(post("/api/tutores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tutorDto)))
                .andReturn().getResponse().getContentAsString();
        String tutorIdStr = objectMapper.readTree(tutorResponse).get("id").asText();
        UUID tutorId = UUID.fromString(tutorIdStr);

        AdocaoRequestDTO adocaoDto = new AdocaoRequestDTO(animalId, tutorId, "APROVADO");
        mockMvc.perform(post("/api/adocoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(adocaoDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("APROVADO"));


        mockMvc.perform(get("/api/animais"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.id == '" + animalIdStr + "')].status").value("ADOTADO"));
    }
}
