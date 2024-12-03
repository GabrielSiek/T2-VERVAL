package SGP_Backend.SGP_Backend.service;

import SGP_Backend.SGP_Backend.dto.BeneficioDTO;
import SGP_Backend.SGP_Backend.model.Beneficio;
import SGP_Backend.SGP_Backend.model.Projeto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import SGP_Backend.SGP_Backend.repository.BeneficioRepository;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;

@AllArgsConstructor
@Service
public class BeneficioService {

    private final BeneficioRepository repository;
    private final ProjetoRepository projetoRepository;

    public List<Beneficio> criarBeneficio(BeneficioDTO beneficioDTO) {
        Beneficio beneficio = Beneficio.fromDTO(beneficioDTO);
        beneficio.setProjeto(projetoRepository.getReferenceById(beneficioDTO.getProjetoId()));
        return Collections.singletonList(repository.save(beneficio));
    }

    public List<Beneficio> buscarBeneficioPorProjetoId(Long id) {
        return repository.buscarPorProjetoId(id);
    }

    public List<Beneficio> atualizarBeneficio(BeneficioDTO beneficioDTO) {
        Beneficio beneficios = Beneficio.fromDTO(beneficioDTO);
        beneficios.setProjeto(projetoRepository.getReferenceById(beneficioDTO.getProjetoId()));
        return (List<Beneficio>) repository.save(beneficios);
    }
}
