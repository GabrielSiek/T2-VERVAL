package SGP_Backend.SGP_Backend.dto;

import SGP_Backend.SGP_Backend.model.ObjetivoEstrategico;
import SGP_Backend.SGP_Backend.model.OpcaoAlinhamentoEstrategico;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonSerialize
@EqualsAndHashCode
@Builder
public class OpcoesObjetivoDTO {
    private List<OpcaoAlinhamentoEstrategico> opcoes;
    private List<ObjetivoEstrategico> objetivosEstrategicos;
}
