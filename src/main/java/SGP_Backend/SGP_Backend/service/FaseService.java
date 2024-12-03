package SGP_Backend.SGP_Backend.service;

import SGP_Backend.SGP_Backend.dto.FaseDTO;
import SGP_Backend.SGP_Backend.enuns.EntregaStatus;
import SGP_Backend.SGP_Backend.exceptions.projeto.ProjetoNaoEncontradoException;
import SGP_Backend.SGP_Backend.model.Fase;
import SGP_Backend.SGP_Backend.repository.EntregaRepository;
import SGP_Backend.SGP_Backend.repository.FaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class FaseService {
    private final FaseRepository faseRepository;

    public Fase buscaFasePorId(Long id){
        try {
            return faseRepository.findById(id).get();
        }catch (Exception e) {
            throw new ProjetoNaoEncontradoException("Fase não encontrada.");
        }
    }
    public List<FaseDTO> getFasesByProjetoId(Long projetoId) {
        List<Fase> fases = faseRepository.findByProjetoId(projetoId);
        List<FaseDTO>fasesDTO =  fases.stream().map(Fase::toDTO).toList();
        populaPorcentagens(fasesDTO);
        return fasesDTO;
    }

    private static void populaPorcentagens(List<FaseDTO> fasesDTO) {
        fasesDTO.forEach(faseDTO -> {
            Map<String, Float> porcentagens = new HashMap<String, Float>();
            float numEntregas = faseDTO.getEntregas().size();
            if(numEntregas != 0){
                for(EntregaStatus status : EntregaStatus.values()){
                    float porcentagem =  faseDTO.getEntregas().stream()
                            .filter(entrega -> entrega.getStatus() == status)
                            .count()/numEntregas;
                    porcentagens.put(status.name(),Float.valueOf(porcentagem));
                }
            }
            else{
                for(EntregaStatus status : EntregaStatus.values()){
                    porcentagens.put(status.name(),Float.valueOf(0));
                }
            }
            faseDTO.setPorcentagens(porcentagens);
        });
    }
}
