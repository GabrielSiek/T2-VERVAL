package SGP_Backend.SGP_Backend.service;

import SGP_Backend.SGP_Backend.dto.RestricaoDTO;
import SGP_Backend.SGP_Backend.exceptions.projeto.ProjetoNaoEncontradoException;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.model.Restricao;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import SGP_Backend.SGP_Backend.repository.RestricaoRepository;
import SGP_Backend.SGP_Backend.utils.ProjetoFactory;
import SGP_Backend.SGP_Backend.utils.RestricaoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

public class RestricaoServiceTest {

    @Mock
    private RestricaoRepository restricaoRepository;

    @Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private RestricaoService restricaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarTodasRestricoesDeUmProjeto() {
        Long idProjeto = 1L;
        Restricao restricao = RestricaoFactory.buildRestricao().build();
        when(restricaoRepository.findAllByProjetoId(idProjeto)).thenReturn(List.of(restricao));

        List<RestricaoDTO> restricoes = restricaoService.buscarTodasRestricoesDeUmProjeto(idProjeto);
        List<RestricaoDTO> restricaoDTO = List.of(Restricao.modelToDTO(restricao));

        assertNotNull(restricoes);
        assertTrue(restricoes.get(0).equals(restricaoDTO.get(0)));
    }

    @Test
    void deveLancarExceptionAoSalvarRestricaoParaProjetoInexistente() {
        Long idProjeto = 1L;
        RestricaoDTO restricaoDTO = RestricaoFactory.buildRestricaoDTO().build();
        when(projetoRepository.findById(idProjeto)).thenReturn(Optional.empty());


        ProjetoNaoEncontradoException exception = assertThrows(
                ProjetoNaoEncontradoException.class,
                () -> restricaoService.salvarRestricao(idProjeto, restricaoDTO)
        );
        assertEquals("Projeto não encontrado.", exception.getMessage());
    }

    @Test
    void deveSalvarRestricaoComSucesso() {
        Long idProjeto = 1L;
        RestricaoDTO restricaoDTO = RestricaoFactory.buildRestricaoDTO().build();
        Projeto projeto = ProjetoFactory.buildProjeto().id(idProjeto).build();
        Restricao restricao = RestricaoFactory.buildRestricao().projeto(projeto).build();

        when(projetoRepository.findById(idProjeto)).thenReturn(Optional.of(projeto));
        when(restricaoRepository.save(any(Restricao.class))).thenReturn(restricao);

        RestricaoDTO restricaoSalva = restricaoService.salvarRestricao(idProjeto, restricaoDTO);

        assertNotNull(restricaoSalva);
        assertEquals(restricaoDTO.getTipo(), restricaoSalva.getTipo());
        assertEquals(restricaoDTO.getDescricao(), restricaoSalva.getDescricao());
    }
}
