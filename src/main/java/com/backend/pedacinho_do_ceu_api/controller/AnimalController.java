package com.backend.pedacinho_do_ceu_api.controller;

import com.backend.pedacinho_do_ceu_api.dto.AnimalRequestDTO;
import com.backend.pedacinho_do_ceu_api.dto.AnimalResponseDTO;
import com.backend.pedacinho_do_ceu_api.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animais")
@Tag(name = "Animais", description = "Endpoints para gestão do catálogo de animais da ONG")
public class AnimalController {

    private final AnimalService service;

    public AnimalController(AnimalService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar Animal", description = "Cadastra um novo animal no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Animal cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados", content = @Content)
    })
    @PostMapping
    public ResponseEntity<AnimalResponseDTO> cadastrar(@RequestBody @Valid AnimalRequestDTO dto) {
        AnimalResponseDTO response = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Listar Animais", description = "Retorna a listagem de todos os animais.")
    @GetMapping
    public ResponseEntity<List<AnimalResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
