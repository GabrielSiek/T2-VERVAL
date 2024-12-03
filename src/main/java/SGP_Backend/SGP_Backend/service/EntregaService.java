package SGP_Backend.SGP_Backend.service;

import SGP_Backend.SGP_Backend.dto.EntregaDTO;
import SGP_Backend.SGP_Backend.model.Entrega;
import SGP_Backend.SGP_Backend.model.Fase;
import SGP_Backend.SGP_Backend.repository.EntregaRepository;
import SGP_Backend.SGP_Backend.repository.FaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EntregaService {

    private final FaseService faseService;
    private final EntregaRepository entregaRepository;

    public List<Entrega> getEntregasByFaseId(Long faseId) {
        return entregaRepository.findByFaseId(faseId);
    }

    public Entrega createEntrega(EntregaDTO entregaDTO) {
        Fase fase = faseService.buscaFasePorId(entregaDTO.getFaseId());
        Entrega entrega = Entrega.FromDTO(entregaDTO);
        entrega.setFase(fase);
        return entregaRepository.save(entrega);
    }

    public Entrega editarEntrega(Long entregaId, EntregaDTO entregaDTO) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new RuntimeException("Entrega não encontrada"));

        entrega.setNome(entregaDTO.getNome());
        entrega.setResponsavel(entregaDTO.getResponsavel());
        entrega.setDataInicio(entregaDTO.getDataInicio());
        entrega.setDataPrevista(entregaDTO.getDataPrevista());
        entrega.setStatus(entregaDTO.getStatus());
        entrega.setRequisitos(entregaDTO.getRequisitos());
        entrega.setMicroEntregas(entregaDTO.getMicroEntregas());
        return entregaRepository.save(entrega);
    }

    public Entrega buscarEntregaPorId(Long id) {
        return entregaRepository.findById(id).orElse(null);
    }

    public void deletarEntrega(Long id) {
        if (entregaRepository.existsById(id)) {
            entregaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Entrega com ID " + id + " não encontrada");
        }
    }


}