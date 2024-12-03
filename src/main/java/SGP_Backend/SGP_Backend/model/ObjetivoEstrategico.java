package SGP_Backend.SGP_Backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@Table(name= "objetivos_estrategicos")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ObjetivoEstrategico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String objetivo;
    private String perspectiva;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "objetivos_estrategicos_id")
    List<IndicadorEstrategico> indicadores;
}
