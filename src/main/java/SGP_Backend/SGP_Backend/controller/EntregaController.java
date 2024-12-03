package SGP_Backend.SGP_Backend.controller;

import SGP_Backend.SGP_Backend.dto.EntregaDTO;
import SGP_Backend.SGP_Backend.dto.FaseDTO;
import SGP_Backend.SGP_Backend.model.Entrega;
import SGP_Backend.SGP_Backend.model.Fase;
import SGP_Backend.SGP_Backend.service.EntregaService;
import SGP_Backend.SGP_Backend.service.FaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
@RequiredArgsConstructor
public class EntregaController {

    private final EntregaService entregaService;
    private final FaseService faseService;

    @GetMapping("/fases/projeto/{projetoId}")
    public ResponseEntity<List<FaseDTO>> buscarFasesPorProjetoId(@PathVariable Long projetoId) {
        List<FaseDTO> fases = faseService.getFasesByProjetoId(projetoId);
        if (fases.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fases);
    }

    @PostMapping
    public ResponseEntity<Entrega> criarEntrega(@RequestBody EntregaDTO entrega) {
        Entrega novaEntrega = entregaService.createEntrega(entrega);
        return ResponseEntity.ok(novaEntrega);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrega> editarEntrega(@PathVariable Long id, @RequestBody EntregaDTO entregaDTO) {
        try {
            Entrega entregaAtualizada = entregaService.editarEntrega(id, entregaDTO);
            return ResponseEntity.ok(entregaAtualizada);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> buscarEntregaPorId(@PathVariable Long id) {
        try {
            Entrega entrega = entregaService.buscarEntregaPorId(id);
            if (entrega == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(entrega);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public void deletarEntrega(@PathVariable Long id) {
        entregaService.deletarEntrega(id);
    }

}
