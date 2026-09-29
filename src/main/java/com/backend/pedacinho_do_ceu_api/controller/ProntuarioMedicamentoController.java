package com.backend.pedacinho_do_ceu_api.controller;

import com.backend.pedacinho_do_ceu_api.dto.ProntuarioRequestDTO;
import com.backend.pedacinho_do_ceu_api.dto.ProntuarioResponseDTO;
import com.backend.pedacinho_do_ceu_api.service.ProntuarioMedicamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/prontuarios")
@Tag(name = "Prontuário Médico", description = "Endpoints para gestão de vacinas e medicamentos dos animais")
public class ProntuarioMedicamentoController {

    private final ProntuarioMedicamentoService service;

    public ProntuarioMedicamentoController(ProntuarioMedicamentoService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar Medicamento", description = "Adiciona uma nova aplicação ao histórico do animal.")
    @PostMapping
    public ResponseEntity<ProntuarioResponseDTO> registrar(@RequestBody @Valid ProntuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }

    @Operation(summary = "Consultar Histórico", description = "Retorna todos os medicamentos aplicados em um animal específico.")
    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<ProntuarioResponseDTO>> listarPorAnimal(@PathVariable UUID animalId) {
        return ResponseEntity.ok(service.listarPorAnimal(animalId));
    }
}
