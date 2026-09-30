package com.backend.pedacinho_do_ceu_api.service;

import com.backend.pedacinho_do_ceu_api.dto.AdocaoRequestDTO;
import com.backend.pedacinho_do_ceu_api.dto.AdocaoResponseDTO;
import com.backend.pedacinho_do_ceu_api.model.Adocao;
import com.backend.pedacinho_do_ceu_api.model.Animal;
import com.backend.pedacinho_do_ceu_api.model.Tutor;
import com.backend.pedacinho_do_ceu_api.repository.AdocaoRepository;
import com.backend.pedacinho_do_ceu_api.repository.AnimalRepository;
import com.backend.pedacinho_do_ceu_api.repository.TutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdocaoService {

    private final AdocaoRepository adocaoRepository;
    private final AnimalRepository animalRepository;
    private final TutorRepository tutorRepository;

    public AdocaoService(AdocaoRepository adocaoRepository, AnimalRepository animalRepository, TutorRepository tutorRepository) {
        this.adocaoRepository = adocaoRepository;
        this.animalRepository = animalRepository;
        this.tutorRepository = tutorRepository;
    }

    @Transactional
    public AdocaoResponseDTO registrar(AdocaoRequestDTO dto) {
        Animal animal = animalRepository.findById(dto.animalId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado."));
        Tutor tutor = tutorRepository.findById(dto.tutorId())
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado."));

        Adocao adocao = new Adocao();
        adocao.setAnimal(animal);
        adocao.setTutor(tutor);
        adocao.setStatus(dto.status().toUpperCase());

        if ("APROVADO".equals(adocao.getStatus())) {
            animal.setStatus("ADOTADO");
            adocao.setDataConclusao(LocalDate.now());
            animalRepository.save(animal);
        }

        Adocao salvo = adocaoRepository.save(adocao);
        return new AdocaoResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<AdocaoResponseDTO> listarPorAnimal(UUID animalId) {
        return adocaoRepository.findByAnimalId(animalId).stream()
                .map(AdocaoResponseDTO::new)
                .collect(Collectors.toList());
    }
}
