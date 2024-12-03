package SGP_Backend.SGP_Backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SGP_Backend.SGP_Backend.domain.Escopo;
import SGP_Backend.SGP_Backend.dto.EscopoDTO;
import SGP_Backend.SGP_Backend.exceptions.ResourceNotFoundException;
import SGP_Backend.SGP_Backend.repository.EscopoRepository;

@Service
public class EscopoService {

    @Autowired
    private EscopoRepository escopoRepository;

    public List<EscopoDTO> findAll() {
        return escopoRepository.findAll().stream()
                .map(EscopoDTO::new)
                .collect(Collectors.toList());
    }

    public EscopoDTO save(EscopoDTO escopoDTO) {
        Escopo escopo = convertToEntity(escopoDTO);
        escopo = escopoRepository.save(escopo);
        return new EscopoDTO(escopo);
    }

    public void deleteById(String id) {
        // Verifica se o Escopo existe antes de deletar
        if (!escopoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Escopo não encontrado");
        }
        escopoRepository.deleteById(id);
    }

    public EscopoDTO findById(String id) {
        return escopoRepository.findById(id)
                .map(EscopoDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Escopo não encontrado"));
    }

    // Métodos auxiliares para conversão entre DTO e entidade
    private Escopo convertToEntity(EscopoDTO escopoDTO) {
        Escopo escopo = new Escopo();
        escopo.setId(escopoDTO.getId());
        escopo.setProdutoFinal(escopoDTO.getProdutoFinal());
        escopo.setDeclaracaoEscopo(escopoDTO.getDeclaracaoEscopo());
        return escopo;
    }
}
