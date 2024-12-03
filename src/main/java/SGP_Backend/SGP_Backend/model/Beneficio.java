package SGP_Backend.SGP_Backend.model;

import SGP_Backend.SGP_Backend.dto.BeneficioDTO;
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
@Table(name = "beneficios")
@EqualsAndHashCode
public class Beneficio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "beneficio_list", joinColumns = @JoinColumn(name = "beneficio_id"))
    @Column(name = "beneficio")
    private List<String> beneficios;

    @OneToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beneficio that = (Beneficio) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(beneficios, that.beneficios) &&
                Objects.equals(projeto, that.projeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beneficios, projeto);
    }

    public static Beneficio fromDTO(BeneficioDTO dto) {
        if (dto == null) {
            return null;
        }
        Beneficio beneficio = new Beneficio();
        beneficio.setId(dto.getId());
        beneficio.setBeneficios(dto.getBeneficios());

        if (dto.getProjetoId() != null) {
            Projeto projeto = new Projeto();
            projeto.setId(dto.getProjetoId());
            beneficio.setProjeto(projeto);
        }

        return beneficio;
    }

    public BeneficioDTO toDTO() {
        BeneficioDTO dto = new BeneficioDTO();
        dto.setId(this.id);
        dto.setBeneficios(this.beneficios);

        if (this.projeto != null) {
            dto.setProjetoId(this.projeto.getId());
        }

        return dto;
    }
}
