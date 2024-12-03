package SGP_Backend.SGP_Backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipes")
@Builder
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "equipe_membros",
            joinColumns = @JoinColumn(name = "equipe_id"),
            inverseJoinColumns = @JoinColumn(name = "membro_id")
    )
    private Set<Membro> membros;

    @ManyToOne
    @JoinColumn(name = "patrocinador_id")
    private Patrocinador patrocinador;
}
