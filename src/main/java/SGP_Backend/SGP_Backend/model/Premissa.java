package SGP_Backend.SGP_Backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import SGP_Backend.SGP_Backend.dto.PremissaDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "premissas")
@Builder
public class Premissa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String acaoParaManutencao;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    public static Premissa fromDTO(PremissaDTO dto) {
        if (dto == null) {
            return null;
        }   

        Premissa premissa = new Premissa();

        premissa.setDescricao(dto.getDescricao());
        premissa.setAcaoParaManutencao(dto.getAcaoParaManutencao());

         // Atribuir o projeto se necessário
         if (dto.getProjetoId() != null) {
            Projeto projeto = new Projeto();
            projeto.setId(dto.getProjetoId());
            premissa.setProjeto(projeto);
        }

        return premissa;
    }
}
