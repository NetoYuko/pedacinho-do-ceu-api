package com.backend.pedacinho_do_ceu_api.controller;

import com.backend.pedacinho_do_ceu_api.dto.AdocaoRequestDTO;
import com.backend.pedacinho_do_ceu_api.dto.AdocaoResponseDTO;
import com.backend.pedacinho_do_ceu_api.service.AdocaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/adocoes")
@Tag(name = "Adoções", description = "Endpoints para gestão de processos de adoção")
public class AdocaoController {

    private final AdocaoService service;

    public AdocaoController(AdocaoService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar Adoção", description = "Inicia um novo processo de adoção vinculando um Tutor a um Animal.")
    @PostMapping
    public ResponseEntity<AdocaoResponseDTO> registrar(@RequestBody @Valid AdocaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }

    @Operation(summary = "Consultar Adoções do Animal", description = "Lista todo o histórico de tentativas de adoção de um animal específico.")
    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<AdocaoResponseDTO>> listarPorAnimal(@PathVariable UUID animalId) {
        return ResponseEntity.ok(service.listarPorAnimal(animalId));
    }
}
