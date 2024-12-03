package SGP_Backend.SGP_Backend.exceptions.restricao;

public class RestricaoNaoEncontradaException extends RuntimeException {
    public RestricaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}