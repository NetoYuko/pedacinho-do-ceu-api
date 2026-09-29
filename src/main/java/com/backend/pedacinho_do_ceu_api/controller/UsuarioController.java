package com.backend.pedacinho_do_ceu_api.controller;

import com.backend.pedacinho_do_ceu_api.dto.UsuarioAtualizarRequestDTO;
import com.backend.pedacinho_do_ceu_api.dto.UsuarioRequestDTO;
import com.backend.pedacinho_do_ceu_api.dto.UsuarioResponseDTO;
import com.backend.pedacinho_do_ceu_api.dto.UsuarioSenhaRequestDTO;
import com.backend.pedacinho_do_ceu_api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gestão de usuários da ONG")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar Usuário")
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @Operation(summary = "Listar Usuários")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Atualizar Dados do Usuário", description = "Atualiza nome, e-mail ou perfil.")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarDadosBasicos(
            @PathVariable UUID id,
            @RequestBody @Valid UsuarioAtualizarRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarDadosBasicos(id, dto));
    }

    @Operation(summary = "Alterar Senha do Usuário", description = "Endpoint exclusivo para atualização segura de credenciais.")
    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> alterarSenha(
            @PathVariable UUID id,
            @RequestBody @Valid UsuarioSenhaRequestDTO dto) {
        service.alterarSenha(id, dto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Excluir Usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
