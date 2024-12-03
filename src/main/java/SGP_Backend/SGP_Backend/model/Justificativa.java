package SGP_Backend.SGP_Backend.model;

import SGP_Backend.SGP_Backend.dto.JustificativaDTO;
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
@Table(name = "justificativa")
@EqualsAndHashCode
public class Justificativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String justificativa;
    @OneToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Justificativa that = (Justificativa) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(justificativa, that.justificativa) &&
                Objects.equals(projeto, that.projeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, justificativa, projeto);
    }

    public static Justificativa fromDTO(JustificativaDTO dto) {
        if (dto == null) {
            return null;
        }
        Justificativa justificativa = new Justificativa();
        justificativa.setId(dto.getId());
        justificativa.setJustificativa(dto.getJustificativa());

        if (dto.getProjetoId() != null) {
            Projeto projeto = new Projeto();
            projeto.setId(dto.getProjetoId());
            justificativa.setProjeto(projeto);
        }

        return justificativa;
    }

    public JustificativaDTO toDTO() {
        JustificativaDTO dto = new JustificativaDTO();
        dto.setId(this.id);
        dto.setJustificativa(this.justificativa);

        if (this.projeto != null) {
            dto.setProjetoId(this.projeto.getId());
        }

        return dto;
    }
}

