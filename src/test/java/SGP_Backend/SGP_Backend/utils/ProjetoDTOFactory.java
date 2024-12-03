package SGP_Backend.SGP_Backend.utils;


import SGP_Backend.SGP_Backend.dto.ProjetoDTO;
import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;


import java.time.LocalDateTime;

public class ProjetoDTOFactory {
    public static ProjetoDTO.ProjetoDTOBuilder buildProjetoResponse(){
        return ProjetoDTO.builder()
                .id(1L)
                .nome("Nome")
                .unidade("Unidade")
                .objetivo("objetivo")
                .dataInicio(String.valueOf(LocalDateTime.of(2020, 8, 11, 14, 30)))
                .dataFim(String.valueOf(LocalDateTime.of(2022, 8, 11, 14, 30)))
                .prazo(String.valueOf(LocalDateTime.of(2021, 8, 11, 14, 30)))
                .tipo(TipoProjeto.ESTRATEGICO)
                .status(StatusProjeto.FINALIZADO);
    }
}
