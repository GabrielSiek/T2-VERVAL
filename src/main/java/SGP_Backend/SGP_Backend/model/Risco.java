package SGP_Backend.SGP_Backend.model;
import SGP_Backend.SGP_Backend.dto.RiscoDTO;
import SGP_Backend.SGP_Backend.enuns.Nivel;
import SGP_Backend.SGP_Backend.enuns.RespostaRisco;
import SGP_Backend.SGP_Backend.enuns.EscalaInteiro;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import jakarta.persistence.*;
import java.util.Objects;

@Builder 
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "risco")
@EqualsAndHashCode
public class Risco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vinculo;
    private String descricao;
    private EscalaInteiro probabilidade;
    private EscalaInteiro impacto;
    private int criticidade;
    private String acao;
    private Nivel nivel;
    private RespostaRisco resposta;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    public static Risco fromDTO(RiscoDTO dto) {
        if (dto == null) {
            return null;
        }
        Risco risco = new Risco();
        risco.setDescricao(dto.getDescricao());
        risco.setProbabilidade(dto.getProbabilidade());
        risco.setImpacto(dto.getImpacto());
        risco.setCriticidade(dto.getCriticidade());
        risco.setAcao(dto.getAcao());
        risco.setNivel(dto.getNivel());
        risco.setResposta(dto.getResposta());

        if (dto.getProjetoId() != null) {
            Projeto projeto = new Projeto();
            projeto.setId(dto.getProjetoId());
            risco.setProjeto(projeto);
        }

        return risco;
    }

    public RiscoDTO toDTO() {
        RiscoDTO dto = new RiscoDTO();
        dto.setId(this.id);
        dto.setDescricao(this.descricao);
        dto.setProbabilidade(this.probabilidade);
        dto.setImpacto(this.impacto);
        dto.setCriticidade(this.criticidade);
        dto.setAcao(this.acao);
        dto.setNivel(this.nivel);
        dto.setResposta(this.resposta);
        dto.setProjetoId(this.projeto.getId());
        return dto;
    }

}