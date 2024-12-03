package SGP_Backend.SGP_Backend.service;
import SGP_Backend.SGP_Backend.dto.ProjetoDTO;
import SGP_Backend.SGP_Backend.exceptions.CorpoVazioException;
import SGP_Backend.SGP_Backend.exceptions.database.DatabaseException;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import SGP_Backend.SGP_Backend.utils.ProjetoDTOFactory;
import SGP_Backend.SGP_Backend.utils.ProjetoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private ProjetoService projetoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarProjetoResponseAoBuscarProjetos() {

        when(projetoRepository.findAllByTipoAndStatus(null, null)).thenReturn(List.of(projetoExemplo()));

        List<ProjetoDTO> projetoResponses = projetoService.buscarTodosProjetosPorStatusETipo(null, null);

        assertNotNull(projetoResponses);
        assertEquals(projetoResponses.get(0), responseExemplo());
    }

    @Test
    public void deveLancarCorpoVazioExceptionQuandoCorpoDeCriacaoDoProjetoForVazio() {
        CorpoVazioException exception = assertThrows(
                CorpoVazioException.class,
                () -> projetoService.criarProjeto(null)
        );
        assertEquals( "O projeto não pode ser nulo", exception.getMessage());
    }

    @Test
    public void deveLancarExceptionQuandoErroAoSalvarProjeto() {
        doThrow(new RuntimeException())
                .when(projetoRepository).save(any());

        DatabaseException exception = assertThrows(
                DatabaseException.class,
                () -> projetoService.criarProjeto(ProjetoFactory.buildProjetoDTO().id(null).build())
        );
        assertEquals("Erro ao salvar o projeto", exception.getMessage());
    }

    private Projeto projetoExemplo(){
        return ProjetoFactory.buildProjeto().build();
    }

    private ProjetoDTO responseExemplo() {
        return ProjetoDTOFactory.buildProjetoResponse().build();
    }

}