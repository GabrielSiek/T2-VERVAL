package SGP_Backend.SGP_Backend.model;

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
@Table(name = "micro_entregas")
public class MicroEntregas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ElementCollection
    @CollectionTable(name = "microentregas_itens", joinColumns = @JoinColumn(name = "microentrega_id"))
    @Column(name = "item")
    private List<String> itens;

    public MicroEntregas(String titulo, List<String> itens) {
        this.titulo = titulo;
        this.itens = itens;
    }
}
