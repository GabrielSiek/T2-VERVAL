package SGP_Backend.SGP_Backend.model;

import SGP_Backend.SGP_Backend.dto.FaseDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "fases")
public class Fase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "fase_id") // cria a chave estrangeira na tabela de Entrega
    private List<Entrega> entregas;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    public Fase(String nome) {
        this.nome = nome;
    }

    public static FaseDTO toDTO(Fase fase) {
        FaseDTO dto = new FaseDTO();
        dto.setId(fase.id);
        dto.setNome(fase.nome);
        dto.setEntregas(fase.entregas);
        dto.setProjetoId(fase.projeto != null ? fase.projeto.getId() : null);
        return dto;
    }
}
