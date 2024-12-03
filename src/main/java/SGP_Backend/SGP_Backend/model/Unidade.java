package SGP_Backend.SGP_Backend.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "unidades")
@EqualsAndHashCode
@Builder
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String direcao;
    private String nome;
    private String setor;
    private String responsabilidade;
    private String email;
    private String ramal;
    @Column(name = "nome_servidor")
    private String nomeServidor;
}
