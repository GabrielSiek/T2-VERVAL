package SGP_Backend.SGP_Backend.verval;

        import SGP_Backend.SGP_Backend.dto.ProjetoDTO;
        import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
        import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.web.client.TestRestTemplate;
        import org.springframework.http.ResponseEntity;
        import org.springframework.test.context.TestPropertySource;
        import org.springframework.http.HttpStatus;
        import org.springframework.jdbc.core.JdbcTemplate;

        import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProjetoSystemTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

        ResponseEntity<ProjetoDTO> response = restTemplate
                .postForEntity("/projetos/criar", projetoDTO, ProjetoDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Projeto Teste", response.getBody().getNome());
        assertEquals("Objetivo do Projeto", response.getBody().getObjetivo());
        assertEquals("Unidade Teste", response.getBody().getUnidade());

        String query = "SELECT COUNT(*) FROM \"projetos\" WHERE \"nome\" = 'Projeto Teste'";
        int count = jdbcTemplate.queryForObject(query, Integer.class);


        assertTrue(count > 0, "O projeto deveria estar no banco de dados");
    }
}
