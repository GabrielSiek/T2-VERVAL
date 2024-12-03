package SGP_Backend.SGP_Backend.controller;
import SGP_Backend.SGP_Backend.dto.JustificativaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import SGP_Backend.SGP_Backend.model.Justificativa;
import SGP_Backend.SGP_Backend.service.JustificativaService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/justificativa")
@RequiredArgsConstructor
public class JustificativaController {

    private final JustificativaService service;

    @PostMapping
    public ResponseEntity<Justificativa> criarJustificativa(@RequestBody JustificativaDTO justificativa) {
        return ResponseEntity.ok(service.criarJustificativa(justificativa));
    }

    @GetMapping
    public ResponseEntity<Justificativa> buscarPorProjetoId(@RequestParam(value = "projetoId", required = true) Long id) {
        return ResponseEntity.ok(service.buscarJustificativaPorProjetoId(id));
    }
}
