package SGP_Backend.SGP_Backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "escopos")
public class Escopo {

    @Id
    private String id;

    private String produtoFinal;

    private String declaracaoEscopo;

    public Escopo() {
    }

    public Escopo(String id, String produtoFinal, String declaracaoEscopo) {
        this.id = id;
        this.produtoFinal = produtoFinal;
        this.declaracaoEscopo = declaracaoEscopo;
    }


}
