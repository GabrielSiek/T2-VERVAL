package SGP_Backend.SGP_Backend.controller;

import SGP_Backend.SGP_Backend.dto.PremissaDTO;
import SGP_Backend.SGP_Backend.model.Premissa;
import SGP_Backend.SGP_Backend.service.PremissaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/premissa")
@RequiredArgsConstructor
@CrossOrigin
public class PremissaController {

  private final PremissaService service;

  @PostMapping("/{projeto}")
  public ResponseEntity<PremissaDTO> cadastrarPremissa(@PathVariable Long projeto, @RequestBody String premissa) {

    PremissaDTO premissaDTO = PremissaDTO
        .builder()
        .descricao(premissa)
        .projetoId(projeto)
        .build();

    Premissa premissaCriada = service.criarPremissa(premissaDTO);
    return ResponseEntity.ok(PremissaDTO.builder()
        .id(premissaCriada.getId())
        .descricao(premissaCriada.getDescricao())
        .acaoParaManutencao(premissaCriada.getAcaoParaManutencao())
        .build());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PremissaDTO> buscarPremissa(@PathVariable Long id) {
    Premissa premissa = service.buscarPremissa(id);
    return ResponseEntity.ok(PremissaDTO.builder()
        .id(premissa.getId())
        .descricao(premissa.getDescricao())
        .acaoParaManutencao(premissa.getAcaoParaManutencao())
        .projetoId(premissa.getProjeto().getId())
        .build());
  }

  @PutMapping("/{id}")
  public ResponseEntity atualizarPremissa(@PathVariable Long id, @RequestBody PremissaDTO premissaDTO) {

    Premissa premissaAtualizada = service.atualizarPremissa(id, premissaDTO);
    return ResponseEntity.ok(
        PremissaDTO.builder()
            .projetoId(premissaAtualizada.getProjeto().getId())
            .descricao(premissaAtualizada.getDescricao())
            .acaoParaManutencao(premissaAtualizada.getAcaoParaManutencao())
            .build()
    );
  }
}
