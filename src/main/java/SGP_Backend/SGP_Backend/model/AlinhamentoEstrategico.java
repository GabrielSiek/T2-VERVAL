package SGP_Backend.SGP_Backend.model;
import SGP_Backend.SGP_Backend.dto.AlinhamentoEstrategicoDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "alinhamentos")
@EqualsAndHashCode
public class AlinhamentoEstrategico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String objetivoEstrategico;
    private boolean principal;
    private String perspectiva;
    private String processoDeCadeiaDeValor;
    private String valorPublico;
    private String resultadoPrincipal;
    private String indicadorEstrategico;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    public static AlinhamentoEstrategico fromDTO(AlinhamentoEstrategicoDTO dto) {
        if (dto == null) {
            return null;
        }
        AlinhamentoEstrategico alinhamento = new AlinhamentoEstrategico();
        alinhamento.setObjetivoEstrategico(dto.getObjetivoEstrategico());
        alinhamento.setProcessoDeCadeiaDeValor(dto.getProcessoDeCadeiaDeValor());
        alinhamento.setPrincipal(dto.isPrincipal());
        alinhamento.setValorPublico(dto.getValorPublico());
        alinhamento.setResultadoPrincipal(dto.getResultadoPrincipal());
        alinhamento.setIndicadorEstrategico(dto.getIndicadorEstrategico());

        // Atribuir o projeto se necessário
        if (dto.getProjetoId() != null) {
            Projeto projeto = new Projeto();
            projeto.setId(dto.getProjetoId());
            alinhamento.setProjeto(projeto);
        }

        return alinhamento;
    }

    // Método para converter de Entidade para DTO
    public AlinhamentoEstrategicoDTO toDTO() {
        AlinhamentoEstrategicoDTO dto = new AlinhamentoEstrategicoDTO();
        dto.setId(this.id);
        dto.setObjetivoEstrategico(this.objetivoEstrategico);
        dto.setProcessoDeCadeiaDeValor(this.processoDeCadeiaDeValor);
        dto.setPrincipal(this.principal);
        dto.setValorPublico(this.valorPublico);
        dto.setResultadoPrincipal(this.resultadoPrincipal);
        dto.setIndicadorEstrategico(this.indicadorEstrategico);

        // Atribuir o projetoId se necessário
        if (this.projeto != null) {
            dto.setProjetoId(this.projeto.getId());
        }

        return dto;
    }
}
