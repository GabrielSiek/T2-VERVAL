package SGP_Backend.SGP_Backend.model;

import SGP_Backend.SGP_Backend.dto.EntregaDTO;
import SGP_Backend.SGP_Backend.enuns.EntregaStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "entregas")
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String responsavel;
    private LocalDateTime dataInicio;
    private LocalDateTime dataPrevista;

    @Enumerated(EnumType.STRING)
    private EntregaStatus status;
    @ElementCollection
    @CollectionTable(name = "entrega_requisitos", joinColumns = @JoinColumn(name = "entrega_id"))
    @Column(name = "requisito")
    private List<String> requisitos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "entrega_id")
    private List<MicroEntregas> microEntregas;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "fase_id", nullable = false)
    private Fase fase;

    public static Entrega FromDTO(EntregaDTO entregaDTO) {
        Entrega entrega = new Entrega();
        entrega.setId(entregaDTO.getId());
        entrega.setNome(entregaDTO.getNome());
        entrega.setResponsavel(entregaDTO.getResponsavel());
        entrega.setDataInicio(entregaDTO.getDataInicio());
        entrega.setDataPrevista(entregaDTO.getDataPrevista());
        entrega.setStatus(entregaDTO.getStatus());
        entrega.setRequisitos(entregaDTO.getRequisitos());
        entrega.setMicroEntregas(entregaDTO.getMicroEntregas());
        return entrega;
    }
}
