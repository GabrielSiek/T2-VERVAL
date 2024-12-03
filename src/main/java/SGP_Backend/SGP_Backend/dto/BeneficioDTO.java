package SGP_Backend.SGP_Backend.dto;

import SGP_Backend.SGP_Backend.model.Beneficio;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class BeneficioDTO {
    private Long id;
    private List<String> beneficios;
    private Long projetoId;

    public BeneficioDTO(Beneficio beneficio) {
        this.id = beneficio.getId();
        this.beneficios = beneficio.getBeneficios();
        this.projetoId = beneficio.getProjeto().getId();
    }

    public BeneficioDTO(Long id, List<String> beneficios, Long projetoId) {
        this.id = id;
        this.beneficios = beneficios;
        this.projetoId = projetoId;
    }

    static public List<BeneficioDTO> deLista(List<Beneficio> beneficioList) {
        List<BeneficioDTO> list = new ArrayList<>();
        beneficioList.forEach(beneficio1 ->
                list.add(new BeneficioDTO(beneficio1))
                );
        return list;
    }
}
