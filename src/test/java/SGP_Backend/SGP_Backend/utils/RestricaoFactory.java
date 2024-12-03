package SGP_Backend.SGP_Backend.utils;

import SGP_Backend.SGP_Backend.dto.RestricaoDTO;
import SGP_Backend.SGP_Backend.enuns.TipoRestricao;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.model.Restricao;

public class RestricaoFactory {

    // Cria uma restrição básica com valores padrão
    public static Restricao.RestricaoBuilder buildRestricao() {
        return Restricao.builder()
                .id(1L)
                .tipo(TipoRestricao.TEMPO_CRONOGRAMA)
                .descricao("Restrição de Tempo de Cronograma")
                .dificuldadeGerada("Alta")
                .projeto(ProjetoFactory.buildProjeto().build());
    }

    // Cria uma restrição específica associada a um projeto passado como parâmetro
    public static Restricao.RestricaoBuilder buildRestricaoComProjeto(Projeto projeto) {
        return Restricao.builder()
                .id(2L) // ID alterado para diferenciar
                .tipo(TipoRestricao.COMUNICACAO)
                .descricao("Restrição de Comunicação")
                .dificuldadeGerada("Média")
                .projeto(projeto);
    }

    // Cria um DTO de restrição com valores padrão para uso em testes de requisição
    public static RestricaoDTO.RestricaoDTOBuilder buildRestricaoDTO() {
        return RestricaoDTO.builder()
                .id(1L)
                .tipo(TipoRestricao.TEMPO_CRONOGRAMA)
                .descricao("Restrição de Tempo de Cronograma")
                .dificuldadeGerada("Alta");
    }
}
