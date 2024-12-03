package SGP_Backend.SGP_Backend.dto;

import SGP_Backend.SGP_Backend.enuns.EntregaStatus;
import SGP_Backend.SGP_Backend.model.Fase;
import SGP_Backend.SGP_Backend.model.MicroEntregas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EntregaDTO {
    private Long id;
    private String nome;
    private String responsavel;
    private LocalDateTime dataInicio;
    private LocalDateTime dataPrevista;
    private EntregaStatus status;
    private List<String> requisitos;
    private List<MicroEntregas> microEntregas;
    private Long faseId;
}
