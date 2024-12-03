package SGP_Backend.SGP_Backend.dto;

import SGP_Backend.SGP_Backend.model.Risco;
import SGP_Backend.SGP_Backend.enuns.Nivel;
import SGP_Backend.SGP_Backend.enuns.RespostaRisco;
import SGP_Backend.SGP_Backend.enuns.EscalaInteiro;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiscoDTO {
    private Long id;
    private String vinculo;
    private String descricao;
    private EscalaInteiro probabilidade;
    private EscalaInteiro impacto;
    private int criticidade;
    private String acao;
    private Nivel nivel;
    private RespostaRisco resposta;
    private Long projetoId;
}
