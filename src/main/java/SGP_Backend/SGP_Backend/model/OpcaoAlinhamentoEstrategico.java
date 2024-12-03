package SGP_Backend.SGP_Backend.model;

import SGP_Backend.SGP_Backend.enuns.TipoDeOpcaoAlinhamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name= "opcoes_alinhamento_estrategico")
@NoArgsConstructor
@AllArgsConstructor
public class OpcaoAlinhamentoEstrategico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String texto;
    @Enumerated(EnumType.STRING)
    TipoDeOpcaoAlinhamento tipo;
}
