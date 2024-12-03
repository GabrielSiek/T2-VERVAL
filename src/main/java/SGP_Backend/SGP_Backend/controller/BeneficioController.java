package SGP_Backend.SGP_Backend.controller;

import SGP_Backend.SGP_Backend.dto.BeneficioDTO;
import SGP_Backend.SGP_Backend.model.Beneficio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/beneficio")
@RequiredArgsConstructor
public class BeneficioController {

    private final SGP_Backend.SGP_Backend.service.BeneficioService service;

    @PostMapping
    public ResponseEntity<List<Beneficio>> criarBeneficio(@RequestBody BeneficioDTO beneficio) {
        return ResponseEntity.ok(service.criarBeneficio(beneficio));
    }

    @GetMapping
    public ResponseEntity<List<Beneficio>> buscarPorProjetoId(@PathVariable(value = "projetoId", required = true) Long id) {
        return ResponseEntity.ok(service.buscarBeneficioPorProjetoId(id));
    }

    @PutMapping
    public ResponseEntity<List<Beneficio>> atualizarBeneficio(@RequestBody BeneficioDTO beneficio) {
        return ResponseEntity.ok(service.atualizarBeneficio(beneficio));
    }
}
