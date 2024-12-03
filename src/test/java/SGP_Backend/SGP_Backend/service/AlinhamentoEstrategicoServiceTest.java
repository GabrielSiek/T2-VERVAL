package SGP_Backend.SGP_Backend.service;

import SGP_Backend.SGP_Backend.dto.AlinhamentoEstrategicoDTO;
import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import SGP_Backend.SGP_Backend.exceptions.projeto.ProjetoNaoEncontradoException;
import SGP_Backend.SGP_Backend.model.AlinhamentoEstrategico;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.repository.AlinhamentoEstrategicoRepository;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import SGP_Backend.SGP_Backend.utils.*;
import SGP_Backend.SGP_Backend.utils.ProjetoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlinhamentoEstrategicoServiceTest {

    @Mock
    private AlinhamentoEstrategicoRepository alinhamentoEstrategicoRepository;

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private ProjetoService projetoService;

    @InjectMocks
    private AlinhamentoEstrategicoService alinhamentoEstrategicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarAlinhamentosPorProjetoId() {
        // Configura o mock para retornar um projeto e seus alinhamentos
        Projeto projeto = projetoExemplo();
        AlinhamentoEstrategico alinhamento1 = AlinhamentoEstrategicoFactory.buildAlinhamentoEstrategico().projeto(projeto).build();
        AlinhamentoEstrategico alinhamento2 = AlinhamentoEstrategicoFactory.buildAlinhamentoEstrategico().projeto(projeto).build();

        when(projetoRepository.findById(projeto.getId())).thenReturn(Optional.of(projeto));
        when(alinhamentoEstrategicoRepository.buscarPorProjetoId(projeto.getId())).thenReturn(List.of(alinhamento1, alinhamento2));

        // Executa o método do service
        List<AlinhamentoEstrategico> alinhamentos = alinhamentoEstrategicoService.buscarAlinhamentoPorProjetoId(projeto.getId());

        // Verificações
        assertNotNull(alinhamentos);
        assertEquals(2, alinhamentos.size());

        // Verifica interações com os mocks
        verify(alinhamentoEstrategicoRepository, times(1)).buscarPorProjetoId(projeto.getId());
    }

    private Projeto projetoExemplo() {
        return ProjetoFactory.buildProjeto()
                .id(1L)
                .nome("Projeto Teste")
                .tipo(TipoProjeto.ESTRATEGICO)
                .status(StatusProjeto.FINALIZADO)
                .build();
    }
}
