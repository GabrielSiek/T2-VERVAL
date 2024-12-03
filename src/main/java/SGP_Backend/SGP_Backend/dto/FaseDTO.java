package SGP_Backend.SGP_Backend.dto;

import SGP_Backend.SGP_Backend.model.Entrega;
import SGP_Backend.SGP_Backend.model.Projeto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FaseDTO {
    private Long id;
    private String nome;
    Map<String, Float> porcentagens;
    private List<Entrega> entregas;
    private Long projetoId;
}
