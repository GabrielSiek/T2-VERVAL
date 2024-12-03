package SGP_Backend.SGP_Backend.enuns;

import lombok.Getter;

@Getter
public enum EscalaInteiro {
    UM(1),
    DOIS(2),
    TRES(3),
    QUATRO(4),
    CINCO(5);

    private final int valor;

    EscalaInteiro(int valor) {
        this.valor = valor;
    }

}
