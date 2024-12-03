package SGP_Backend.SGP_Backend.dto;

import SGP_Backend.SGP_Backend.model.AlinhamentoEstrategico;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlinhamentoEstrategicoDTO {
    private Long id;
    private String objetivoEstrategico;
    private String processoDeCadeiaDeValor;
    private String perspectiva;
    private boolean principal;
    private String valorPublico;
    private String resultadoPrincipal;
    private String indicadorEstrategico;
    private Long projetoId;
}
