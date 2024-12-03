package SGP_Backend.SGP_Backend.dto;

import SGP_Backend.SGP_Backend.domain.Escopo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EscopoDTO {
    private String id;
    private String produtoFinal;
    private String declaracaoEscopo;

    public EscopoDTO() {
    }

    public EscopoDTO(Escopo escopo) {
        this.id = escopo.getId();
        this.produtoFinal = escopo.getProdutoFinal();
        this.declaracaoEscopo = escopo.getDeclaracaoEscopo();
    }

    public EscopoDTO(String id, String produtoFinal, String declaracaoEscopo) {
        this.id = id;
        this.produtoFinal = produtoFinal;
        this.declaracaoEscopo = declaracaoEscopo;

    }
    
}
