package SGP_Backend.SGP_Backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PremissaDTO {
    private Long id;
    private String descricao;
    private String acaoParaManutencao;
    private Long projetoId;
}
