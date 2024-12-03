package SGP_Backend.SGP_Backend.dto;

import SGP_Backend.SGP_Backend.model.Justificativa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JustificativaDTO {
    private Long id;
    private String justificativa;
    private Long projetoId;
}
