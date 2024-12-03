package SGP_Backend.SGP_Backend.dto;

import SGP_Backend.SGP_Backend.enuns.TipoRestricao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class RestricaoDTO {
    private Long id;
    private TipoRestricao tipo;
    private String descricao;
    private String dificuldadeGerada;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestricaoDTO that = (RestricaoDTO) o;
        return Objects.equals(id, that.id) && tipo == that.tipo && Objects.equals(descricao, that.descricao) && Objects.equals(dificuldadeGerada, that.dificuldadeGerada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, descricao, dificuldadeGerada);
    }
}
