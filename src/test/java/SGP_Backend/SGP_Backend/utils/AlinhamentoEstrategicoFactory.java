package SGP_Backend.SGP_Backend.utils;

import SGP_Backend.SGP_Backend.dto.AlinhamentoEstrategicoDTO;

import SGP_Backend.SGP_Backend.model.AlinhamentoEstrategico;
import SGP_Backend.SGP_Backend.model.Projeto;
public class AlinhamentoEstrategicoFactory {

    public static AlinhamentoEstrategico.AlinhamentoEstrategicoBuilder buildAlinhamentoEstrategico() {
        return AlinhamentoEstrategico.builder()
                .id(1L)  // ID de exemplo
                .objetivoEstrategico("Objetivo Estratégico Exemplo")
                .principal(true)  // Define se é o principal
                .perspectiva("Perspectiva Exemplo")
                .processoDeCadeiaDeValor("Processo de Cadeia de Valor Exemplo")
                .valorPublico("Valor Público Exemplo")
                .resultadoPrincipal("Resultado Principal Exemplo")
                .indicadorEstrategico("Indicador Estratégico Exemplo")
                .projeto(Projeto.builder().build());  // Associa um projeto ao alinhamento
    }

    public static AlinhamentoEstrategicoDTO.AlinhamentoEstrategicoDTOBuilder buildAlinhamentoEstrategicoDTO() {
        return AlinhamentoEstrategicoDTO.builder()
                .id(1L)  // ID de exemplo
                .objetivoEstrategico("Objetivo Estratégico DTO Exemplo")
                .principal(true)  // Define se é o principal
                .perspectiva("Perspectiva DTO Exemplo")
                .processoDeCadeiaDeValor("Processo de Cadeia de Valor DTO Exemplo")
                .valorPublico("Valor Público DTO Exemplo")
                .resultadoPrincipal("Resultado Principal DTO Exemplo")
                .indicadorEstrategico("Indicador Estratégico DTO Exemplo")
                .projetoId(1L);  // ID do projeto associado
    }
}
