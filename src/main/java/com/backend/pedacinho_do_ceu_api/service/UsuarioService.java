package com.backend.pedacinho_do_ceu_api.service;

import com.backend.pedacinho_do_ceu_api.dto.UsuarioAtualizarRequestDTO;
import com.backend.pedacinho_do_ceu_api.dto.UsuarioRequestDTO;
import com.backend.pedacinho_do_ceu_api.dto.UsuarioResponseDTO;
import com.backend.pedacinho_do_ceu_api.dto.UsuarioSenhaRequestDTO;
import com.backend.pedacinho_do_ceu_api.model.Usuario;
import com.backend.pedacinho_do_ceu_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if (repository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado no sistema.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setPerfil(dto.perfil());
        usuario.setSenhaHash(dto.senha());

        Usuario salvo = repository.save(usuario);
        return new UsuarioResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioResponseDTO atualizarDadosBasicos(UUID id, UsuarioAtualizarRequestDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Se o email foi enviado e é diferente do atual, verifica se já existe no banco
        if (dto.email() != null && !dto.email().isBlank() && !dto.email().equals(usuario.getEmail())) {
            if (repository.findByEmail(dto.email()).isPresent()) {
                throw new RuntimeException("Este e-mail já está sendo utilizado por outro usuário.");
            }
            usuario.setEmail(dto.email());
        }

        if (dto.nome() != null && !dto.nome().isBlank()) {
            usuario.setNome(dto.nome());
        }

        if (dto.perfil() != null && !dto.perfil().isBlank()) {
            usuario.setPerfil(dto.perfil());
        }

        return new UsuarioResponseDTO(repository.save(usuario));
    }

    @Transactional
    public void alterarSenha(UUID id, UsuarioSenhaRequestDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Temporário: validação de senha em texto puro.
        // Na próxima etapa de JWT, trocaremos por passwordEncoder.matches()
        if (!usuario.getSenhaHash().equals(dto.senhaAtual())) {
            throw new RuntimeException("A senha atual informada está incorreta.");
        }

        // Temporário: salvando em texto puro.
        // Na próxima etapa, aplicaremos passwordEncoder.encode(dto.novaSenha())
        usuario.setSenhaHash(dto.novaSenha());
        repository.save(usuario);
    }

    @Transactional
    public void excluir(UUID id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        repository.delete(usuario);
    }
}
