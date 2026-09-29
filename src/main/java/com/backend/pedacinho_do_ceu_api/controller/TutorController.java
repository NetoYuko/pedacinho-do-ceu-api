package com.backend.pedacinho_do_ceu_api.controller;

import com.backend.pedacinho_do_ceu_api.dto.TutorRequestDTO;
import com.backend.pedacinho_do_ceu_api.dto.TutorResponseDTO;
import com.backend.pedacinho_do_ceu_api.service.TutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tutores")
@Tag(name = "Tutores", description = "Endpoints para gestão de interessados em adoção")
public class TutorController {

    private final TutorService service;

    public TutorController(TutorService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar Tutor", description = "Registra uma nova pessoa interessada em adotar.")
    @PostMapping
    public ResponseEntity<TutorResponseDTO> cadastrar(@RequestBody @Valid TutorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @Operation(summary = "Listar Tutores", description = "Retorna o cadastro de todos os tutores.")
    @GetMapping
    public ResponseEntity<List<TutorResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Atualizar Tutor", description = "Altera os dados de contato ou endereço de um tutor.")
    @PutMapping("/{id}")
    public ResponseEntity<TutorResponseDTO> atualizar(@PathVariable UUID id, @RequestBody @Valid TutorRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Excluir Tutor", description = "Remove um tutor do sistema (apenas se não houver adoções vinculadas).")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
