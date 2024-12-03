package SGP_Backend.SGP_Backend.verval;

import SGP_Backend.SGP_Backend.controller.ProjetoController;
import SGP_Backend.SGP_Backend.dto.ProjetoDTO;
import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ProjetoIntegrationTest {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ProjetoController projetoController;

    @BeforeEach
    public void setUp() {
        projetoRepository.deleteAll();
    }

    @Test
    public void testCriarProjeto() {
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

        ResponseEntity<ProjetoDTO> response = projetoController.criarProjeto(projetoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals("Projeto Teste", response.getBody().getNome());
        assertEquals("Objetivo do Projeto", response.getBody().getObjetivo());
        assertEquals("Unidade Teste", response.getBody().getUnidade());

        Projeto projetoSalvo = projetoRepository.findById(response.getBody().getId()).orElseThrow();
        assertEquals("Projeto Teste", projetoSalvo.getNome());
        assertEquals("Objetivo do Projeto", projetoSalvo.getObjetivo());
        assertEquals("Unidade Teste", projetoSalvo.getUnidade());
        assertEquals("2024-11-28", projetoSalvo.getDataInicio());
        assertEquals("2024-11-29", projetoSalvo.getDataFim());
        assertEquals("2024-12-01", projetoSalvo.getPrazo());
    }
}
