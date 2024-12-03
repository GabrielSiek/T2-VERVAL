package SGP_Backend.SGP_Backend.model;

import SGP_Backend.SGP_Backend.dto.RestricaoDTO;
import SGP_Backend.SGP_Backend.enuns.TipoRestricao;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "restricoes")
public class Restricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoRestricao tipo;
    private String descricao;
    @Column(name = "dificuldade_gerada")
    private String dificuldadeGerada;
    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    public Restricao(TipoRestricao tipo, String descricao, String dificuldadeGerada, Projeto projeto) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.dificuldadeGerada = dificuldadeGerada;
        this.projeto = projeto;
    }

    public static RestricaoDTO modelToDTO(Restricao restricao) {
        return RestricaoDTO.builder()
                .id(restricao.id)
                .tipo(restricao.tipo)
                .descricao(restricao.descricao)
                .dificuldadeGerada(restricao.dificuldadeGerada)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restricao restricao = (Restricao) o;
        return Objects.equals(id, restricao.id) && tipo == restricao.tipo && Objects.equals(descricao, restricao.descricao) && Objects.equals(dificuldadeGerada, restricao.dificuldadeGerada) && Objects.equals(projeto, restricao.projeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, descricao, dificuldadeGerada, projeto);
    }

    public static Restricao dtoToModel(RestricaoDTO restricaoDTO) {
        return Restricao.builder()
                .id(restricaoDTO.getId())
                .tipo(restricaoDTO.getTipo())
                .descricao(restricaoDTO.getDescricao())
                .dificuldadeGerada(restricaoDTO.getDificuldadeGerada())
                .build();
    }


}
