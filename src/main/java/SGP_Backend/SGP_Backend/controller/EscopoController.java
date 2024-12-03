package SGP_Backend.SGP_Backend.controller;

import SGP_Backend.SGP_Backend.dto.EscopoDTO;
import SGP_Backend.SGP_Backend.service.EscopoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Define que esta classe é um controlador REST
@RequestMapping("/api/escopos")  // Define o mapeamento base para todos os endpoints
public class EscopoController {

    private final EscopoService escopoService;

    @Autowired  // Injeta automaticamente o EscopoService
    public EscopoController(EscopoService escopoService) {
        this.escopoService = escopoService;
    }

    @GetMapping
    public ResponseEntity<List<EscopoDTO>> getAllEscopos() {
        List<EscopoDTO> escopos = escopoService.findAll();
        return ResponseEntity.ok(escopos);  // Retorna 200 OK com a lista de EscopoDTO
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscopoDTO> getEscopoById(@PathVariable String id) {
        EscopoDTO escopo = escopoService.findById(id);
        return ResponseEntity.ok(escopo);  // Retorna 200 OK com o EscopoDTO encontrado
    }

    @PostMapping
    public ResponseEntity<EscopoDTO> createEscopo(@RequestBody EscopoDTO escopoDTO) {
        EscopoDTO newEscopo = escopoService.save(escopoDTO);
        return new ResponseEntity<>(newEscopo, HttpStatus.CREATED);  // Retorna 201 CREATED com o EscopoDTO criado
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscopoDTO> updateEscopo(@PathVariable String id, @RequestBody EscopoDTO escopoDTO) {
        escopoService.findById(id);  // Verifica se o escopo existe
        escopoDTO.setId(id);  // Garante que o ID está correto
        EscopoDTO updatedEscopo = escopoService.save(escopoDTO);
        return ResponseEntity.ok(updatedEscopo);  // Retorna 200 OK com o EscopoDTO atualizado
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEscopo(@PathVariable String id) {
        escopoService.deleteById(id);
        return ResponseEntity.noContent().build();  // Retorna 204 NO CONTENT após a exclusão
    }
}
