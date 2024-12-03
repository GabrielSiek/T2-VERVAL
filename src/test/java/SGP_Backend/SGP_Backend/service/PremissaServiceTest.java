package SGP_Backend.SGP_Backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import SGP_Backend.SGP_Backend.dto.PremissaDTO;
import SGP_Backend.SGP_Backend.model.Premissa;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.repository.PremissaRepository;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PremissaServiceTest {

  @InjectMocks
  private PremissaService premissaService;

  @Mock
  private PremissaRepository premissaRepository;

  @Mock
  private ProjetoRepository projetoRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void deveBuscarPremissaQuandoExistirIdNoBanco(){

      Premissa premissa = Premissa.builder()
          .id(1L)
          .projeto(Projeto.builder()
              .id(2L)
              .build())
          .descricao("Teste")
          .acaoParaManutencao("Teste")
          .build();

      when(projetoRepository.findById(premissa.getProjeto().getId())).thenReturn(Optional.of(premissa.getProjeto()));

      when(premissaRepository.findById(1L)).thenReturn(Optional.of(premissa));

      Premissa premissaBuscada = premissaService.buscarPremissa(1L);

      assertEquals(premissaBuscada.getId(), premissa.getId());
      assertEquals(premissaBuscada.getDescricao(), premissa.getDescricao());
      assertEquals(premissaBuscada.getAcaoParaManutencao(), premissa.getAcaoParaManutencao());
  }

  @Test
  public void deveAtualizarPremissaQuandoDadosEstiverCorretos(){

        Premissa premissa = Premissa.builder()
            .id(1L)
            .projeto(Projeto.builder()
                .id(3L)
                .build())
            .descricao("Teste")
            .acaoParaManutencao("Teste1")
            .build();

        PremissaDTO premissaDTO = PremissaDTO.builder()
            .projetoId(3L)
            .descricao("Teste")
            .acaoParaManutencao("Teste1")
            .build();

        when(projetoRepository.findById(premissa.getProjeto().getId())).thenReturn(Optional.of(premissa.getProjeto()));
        when(premissaRepository.findById(1L)).thenReturn(Optional.of(premissa));
        when(premissaRepository.save(premissa)).thenReturn(premissa);

        Premissa premissaAtualizada = premissaService.atualizarPremissa(1L, premissaDTO);

        assertEquals(premissaAtualizada.getId(), premissa.getId());
        assertEquals(premissaAtualizada.getDescricao(), premissa.getDescricao());
        assertEquals(premissaAtualizada.getAcaoParaManutencao(), premissa.getAcaoParaManutencao());
  }


  @Test
  public void deveCriarUmaPremissaNova(){

     PremissaDTO premissaDTO = PremissaDTO.builder()
         .descricao("Teste")
         .projetoId(1L)
         .build();

     Premissa premissa = Premissa.fromDTO(premissaDTO);

     when(premissaRepository.save(premissa)).thenReturn(premissa);

     Premissa premissaCriada = premissaService.criarPremissa(premissaDTO);

     assertEquals(premissaCriada.getDescricao(), premissa.getDescricao());
     assertEquals(premissaCriada.getAcaoParaManutencao(), premissa.getAcaoParaManutencao());
     assertEquals(premissaCriada.getProjeto().getId(), premissa.getProjeto().getId());

  }

  @Test
  public void deveLancarExcecaoAoTentarSalvarUmaPremissaNova(){

      PremissaDTO premissaDTO = PremissaDTO.builder()
          .descricao("Teste")
          .projetoId(1L)
          .build();

      Premissa premissa = Premissa.fromDTO(premissaDTO);

      when(premissaRepository.save(premissa)).thenThrow(new RuntimeException());

    String message = assertThrows(RuntimeException.class, () -> premissaService.criarPremissa(premissaDTO)).getMessage();
    assertEquals("Erro ao salvar a premissa", message);
  }

}