package SGP_Backend.SGP_Backend.service;

import SGP_Backend.SGP_Backend.dto.JustificativaDTO;
import SGP_Backend.SGP_Backend.model.Justificativa;
import SGP_Backend.SGP_Backend.model.Projeto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import SGP_Backend.SGP_Backend.repository.JustificativaRepository;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;

@AllArgsConstructor
@Service
public class JustificativaService {

    private final JustificativaRepository repository;
    private final ProjetoRepository projetoRepository;

    public Justificativa criarJustificativa(JustificativaDTO justificativaDTO) {
        Justificativa justificativa = Justificativa.fromDTO(justificativaDTO);
        justificativa.setProjeto(projetoRepository.getReferenceById(justificativaDTO.getId()));
        return repository.save(justificativa);
    }

    public Justificativa buscarJustificativaPorProjetoId(Long id) {
        return repository.buscarPorProjetoId(id);
    }
}
