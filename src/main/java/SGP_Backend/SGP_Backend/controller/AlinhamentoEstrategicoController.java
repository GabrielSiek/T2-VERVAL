package SGP_Backend.SGP_Backend.controller;

import SGP_Backend.SGP_Backend.dto.AlinhamentoEstrategicoDTO;
import SGP_Backend.SGP_Backend.dto.OpcoesObjetivoDTO;
import SGP_Backend.SGP_Backend.service.AlinhamentoEstrategicoService;
import SGP_Backend.SGP_Backend.model.AlinhamentoEstrategico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alinhamento-estrategico")
@RequiredArgsConstructor
public class AlinhamentoEstrategicoController {

    private final AlinhamentoEstrategicoService service;

    @PostMapping
    public ResponseEntity<AlinhamentoEstrategico> criarAlinhamentoEstrategico(@RequestBody AlinhamentoEstrategicoDTO alinhamentoEstrategico) {
        return ResponseEntity.ok(service.CriarAlinhamentoEstrategico(alinhamentoEstrategico));
    }

    @GetMapping
    public ResponseEntity<List<AlinhamentoEstrategico>> buscarPorProjetoId(@RequestParam(value = "projetoId", required = true) Long id) {
        return ResponseEntity.ok(service.buscarAlinhamentoPorProjetoId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlinhamentoEstrategico> atualizar(@PathVariable Long id, @RequestBody AlinhamentoEstrategico alinhamento) {
        return ResponseEntity.ok(service.atualizarAlinhamentoEstrategico(id, alinhamento));
    }

    @GetMapping("/opcoes")
    public ResponseEntity<OpcoesObjetivoDTO> buscarOpcoes() {
        return ResponseEntity.ok(service.buscarOpcoes());
    }

}

