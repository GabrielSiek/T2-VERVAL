package SGP_Backend.SGP_Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.*;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
@Table(name= "membros")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Membro extends Usuario {
    private String funcao;
    private String lotacao;
    private String setor;
    private String ramal;
}
