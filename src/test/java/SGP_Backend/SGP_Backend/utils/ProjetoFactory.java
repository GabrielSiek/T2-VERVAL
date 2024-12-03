package SGP_Backend.SGP_Backend.utils;

import SGP_Backend.SGP_Backend.dto.ProjetoDTO;
import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import SGP_Backend.SGP_Backend.model.*;

import java.time.LocalDateTime;
import java.util.Set;

public class ProjetoFactory {
    public static Projeto.ProjetoBuilder buildProjeto(){
        return Projeto.builder()
                .id(1L)
                .nome("Nome")
                .versao("Versao")
                .unidade("Unidade")
                .objetivo("objetivo")
                .dataInicio(String.valueOf(LocalDateTime.of(2020, 8, 11, 14, 30)))
                .dataFim(String.valueOf(LocalDateTime.of(2022, 8, 11, 14, 30)))
                .prazo(String.valueOf(LocalDateTime.of(2021, 8, 11, 14, 30)))
                .tipo(TipoProjeto.ESTRATEGICO)
                .status(StatusProjeto.FINALIZADO);
    }

    public static ProjetoDTO.ProjetoDTOBuilder buildProjetoDTO(){
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

    public static Projeto.ProjetoBuilder buildProjetoComEquipeEUnidades(){
        return buildProjeto()
                .equipe(buildEquipe().build())
                .outrasUnidades(Set.of(buildUnidades().build()));

    }

    private static Unidade.UnidadeBuilder buildUnidades() {
        return Unidade.builder()
                .id(1L)
                .direcao("direcao")
                .email("email.unidade@email.com")
                .ramal("123321")
                .setor("setor")
                .nomeServidor("nome servidor")
                .nome("unidade")
                .responsabilidade("responsabilidade");
    }


    public static Equipe.EquipeBuilder buildEquipe(){
        return Equipe.builder()
                .id(1L)
                .membros(Set.of(buildMembro().build()))
                .patrocinador(buildPatrocinador().build());

    }

    public static Membro.MembroBuilder buildMembro(){
        return Membro.builder()
                .id(2L)
                .email("membro@email.com")
                .senha("senhamembro")
                .nome("membro")
                .tipo(Usuario.TipoUsuario.FUNCIONARIO);


    }

    public static Patrocinador.PatrocinadorBuilder buildPatrocinador(){
        return Patrocinador.builder()
                .id(1L)
                .email("patrocinador@email.com")
                .senha("senhapatrocinador")
                .nome("patrocinador")
                .tipo(Usuario.TipoUsuario.PATROCINADOR)
                .posicao("posicao");
    }
}
