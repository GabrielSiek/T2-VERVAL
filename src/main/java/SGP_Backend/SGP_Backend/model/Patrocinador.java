package SGP_Backend.SGP_Backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.*;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
@Table(name= "patrocinadores")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Patrocinador extends Usuario {
    private String posicao;
}
