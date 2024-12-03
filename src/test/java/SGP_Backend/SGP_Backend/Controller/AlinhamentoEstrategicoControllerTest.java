package SGP_Backend.SGP_Backend.Controller;

import SGP_Backend.SGP_Backend.dto.AlinhamentoEstrategicoDTO;
import SGP_Backend.SGP_Backend.model.AlinhamentoEstrategico;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.repository.AlinhamentoEstrategicoRepository;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import SGP_Backend.SGP_Backend.utils.ProjetoFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AlinhamentoEstrategicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private AlinhamentoEstrategicoRepository alinhamentoEstrategicoRepository;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @BeforeEach
    public void setup() {
        projetoRepository.deleteAll();
        alinhamentoEstrategicoRepository.deleteAll();

        // Cria um projeto e alinhamentos associados para teste
        Projeto projeto = new Projeto();
        projeto.setNome("Projeto Teste");
        projetoRepository.save(projeto);

        AlinhamentoEstrategico alinhamento1 = AlinhamentoEstrategico.builder()
                .objetivoEstrategico("Objetivo 1")
                .principal(true)
                .perspectiva("Perspectiva 1")
                .projeto(projeto)
                .build();

        AlinhamentoEstrategico alinhamento2 = AlinhamentoEstrategico.builder()
                .objetivoEstrategico("Objetivo 2")
                .principal(false)
                .perspectiva("Perspectiva 2")
                .projeto(projeto)
                .build();

        alinhamentoEstrategicoRepository.saveAll(List.of(alinhamento1, alinhamento2));
    }

    @Test
    public void deveRetornarListaDeAlinhamentosPorProjetoId() throws Exception {
        // Obter o projeto salvo no banco
        Projeto projeto = projetoRepository.findAll().get(0);

        // Realiza a requisição GET para o endpoint com o ID do projeto
        mockMvc.perform(get("/alinhamento-estrategico")
                        .param("projetoId", projeto.getId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].objetivoEstrategico", is("Objetivo 1")))
                .andExpect(jsonPath("$[0].principal", is(true)))
                .andExpect(jsonPath("$[0].perspectiva", is("Perspectiva 1")))
                .andExpect(jsonPath("$[1].objetivoEstrategico", is("Objetivo 2")))
                .andExpect(jsonPath("$[1].principal", is(false)))
                .andExpect(jsonPath("$[1].perspectiva", is("Perspectiva 2")));
    }

    @Test
    public void deveRetornarListaVaziaQuandoProjetoNaoTemAlinhamentos() throws Exception {
        Projeto projetoSemAlinhamentos = new Projeto();
        projetoSemAlinhamentos.setNome("Projeto Sem Alinhamentos");
        projetoRepository.save(projetoSemAlinhamentos);

        mockMvc.perform(get("/alinhamento-estrategico")
                        .param("projetoId", projetoSemAlinhamentos.getId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void deveCriarAlinhamento() throws Exception {
        Projeto projeto = projetoRepository.save(ProjetoFactory.buildProjeto().id(null).build());
        AlinhamentoEstrategicoDTO novoAlinhamento = new AlinhamentoEstrategicoDTO();
        novoAlinhamento.setObjetivoEstrategico("Novo Objetivo");
        novoAlinhamento.setValorPublico("Novo Valor Público");
        novoAlinhamento.setProjetoId(projeto.getId());

        mockMvc.perform(post("/alinhamento-estrategico")
                        .content(jacksonObjectMapper.writeValueAsString(novoAlinhamento))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.objetivoEstrategico").value("Novo Objetivo"))
                .andExpect(jsonPath("$.valorPublico").value("Novo Valor Público"));
    }

}
