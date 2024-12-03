package SGP_Backend.SGP_Backend.dto;

import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import SGP_Backend.SGP_Backend.model.Projeto;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonSerialize
@EqualsAndHashCode
@Builder
public class ProjetoDTO {


    private Long id;
    private String nome;
    private String objetivo;
    private String unidade;
    //@JsonFormat(pattern = "yyyy-MM-dd")
    private String dataInicio;
    //@JsonFormat(pattern = "yyyy-MM-dd")
    private String dataFim;
    //@JsonFormat(pattern = "yyyy-MM-dd")
    private String prazo;
    private TipoProjeto tipo;
    private StatusProjeto status;

    public ProjetoDTO(String projetoTeste, String objetivoDoProjeto, String unidadeTeste, String date, String date1, String date2) {
    }

    public static List<ProjetoDTO> converteProjetos(List<Projeto> projetos){
        return projetos.stream().map(projeto ->{
                    return ProjetoDTO.builder()
                            .id(projeto.getId())
                            .nome(projeto.getNome())
                            .unidade(projeto.getUnidade())
                            .objetivo(projeto.getObjetivo())
                            .dataInicio(projeto.getDataInicio())
                            .dataFim(projeto.getDataFim())
                            .prazo(projeto.getPrazo())
                            .tipo(projeto.getTipo())
                            .status(projeto.getStatus())
                            .build();
                    }
        ).toList();
    }

    public static Projeto criarProjetoFromDTO(ProjetoDTO projetoDTO){
        return new Projeto(projetoDTO.getNome(), projetoDTO.getUnidade(), projetoDTO.getDataInicio(), projetoDTO.getPrazo());
    }

}
