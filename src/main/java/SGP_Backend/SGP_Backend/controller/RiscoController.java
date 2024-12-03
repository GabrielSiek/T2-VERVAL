package SGP_Backend.SGP_Backend.controller;

import SGP_Backend.SGP_Backend.dto.RiscoDTO;
import SGP_Backend.SGP_Backend.model.Risco;
import SGP_Backend.SGP_Backend.service.RiscoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/risco")
@RequiredArgsConstructor
public class RiscoController {

    private final RiscoService service;

    @PostMapping
    public ResponseEntity<Risco> criarRisco(@RequestBody RiscoDTO risco) {
        return ResponseEntity.ok(service.criarRisco(risco));
    }

    @GetMapping
    public ResponseEntity<List<Risco>> buscarPorProjetoId(@RequestParam(value = "projetoId", required = true) Long id) {
        return ResponseEntity.ok(service.buscarRiscoPorProjetoId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Risco> atualizar(@PathVariable Long id, @RequestBody Risco risco) {
        return ResponseEntity.ok(service.atualizarRisco(id, risco));
    }

}