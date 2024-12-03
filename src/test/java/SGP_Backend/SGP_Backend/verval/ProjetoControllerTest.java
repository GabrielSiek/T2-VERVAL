package SGP_Backend.SGP_Backend.verval;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import SGP_Backend.SGP_Backend.controller.ProjetoController;
import SGP_Backend.SGP_Backend.dto.ProjetoDTO;
import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class ProjetoControllerTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private ProjetoController projetoController;

    private ProjetoDTO projetoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        projetoDTO = new ProjetoDTO();
        projetoDTO.setNome("Projeto Teste");
        projetoDTO.setObjetivo("Objetivo do Projeto");
        projetoDTO.setUnidade("Unidade Teste");
        projetoDTO.setDataInicio(String.valueOf(LocalDateTime.of(2024, 11, 28, 10, 0, 0)));
        projetoDTO.setDataFim(String.valueOf(LocalDateTime.of(2024, 12, 31, 23, 59, 59)));
        projetoDTO.setPrazo(String.valueOf(LocalDateTime.of(2025, 1, 1, 0, 0, 0)));
        projetoDTO.setTipo(TipoProjeto.ESTRATEGICO);
        projetoDTO.setStatus(StatusProjeto.EM_ANDAMENTO);
    }

    @Test
    void testCriarProjeto_Success() throws Exception {
        ProjetoDTO projetoDTO = new ProjetoDTO(
                null,
                "Projeto Teste",
                "Objetivo do Projeto",
                "Unidade Teste",
                "2024-11-28",
                "2024-11-29",
                "2024-12-01",
                TipoProjeto.TATICO,
                StatusProjeto.EM_ANDAMENTO
        );

        Projeto projetoSalvo = new Projeto(
                projetoDTO.getNome(),
                projetoDTO.getObjetivo(),
                projetoDTO.getUnidade(),
                projetoDTO.getDataInicio(),
                projetoDTO.getDataFim(),
                projetoDTO.getPrazo()
        );

        when(projetoRepository.save(any(Projeto.class))).thenReturn(projetoSalvo);

        ResponseEntity<ProjetoDTO> response = projetoController.criarProjeto(projetoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Projeto Teste", response.getBody().getNome());
        assertEquals("Objetivo do Projeto", response.getBody().getObjetivo());
        assertEquals("Unidade Teste", response.getBody().getUnidade());

        assertEquals("2024-11-28", response.getBody().getDataInicio());
        assertEquals("2024-11-29", response.getBody().getDataFim());
        assertEquals("2024-12-01", response.getBody().getPrazo());
    }

    @Test
    void testCriarProjeto_Error() throws Exception {
        when(projetoRepository.save(any(Projeto.class))).thenThrow(new RuntimeException("Erro ao salvar"));

        ResponseEntity<ProjetoDTO> response = projetoController.criarProjeto(projetoDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}
