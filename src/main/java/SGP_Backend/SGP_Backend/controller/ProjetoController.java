package SGP_Backend.SGP_Backend.controller;

import SGP_Backend.SGP_Backend.dto.ProjetoDTO;
import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import SGP_Backend.SGP_Backend.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
@RequiredArgsConstructor
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    private final ProjetoService service;



    @GetMapping
    public ResponseEntity<List<ProjetoDTO>> buscarTodos(@RequestParam(value = "tipo", required = false) TipoProjeto tipo,
                                                        @RequestParam(value = "status", required = false) StatusProjeto status) {
        return ResponseEntity.ok(service.buscarTodosProjetosPorStatusETipo(tipo, status));
    }
    @PostMapping("/criar")
    public ResponseEntity<ProjetoDTO> criarProjeto(@RequestBody ProjetoDTO projetoResponse) {
        try {
            Projeto projeto = new Projeto(
                    projetoResponse.getNome(),
                    projetoResponse.getObjetivo(),
                    projetoResponse.getUnidade(),
                    projetoResponse.getDataInicio(),
                    projetoResponse.getDataFim(),
                    projetoResponse.getPrazo()

            );

            Projeto projetoSalvo = projetoRepository.save(projeto);

            ProjetoDTO projetoDTO = new ProjetoDTO(
                    projetoSalvo.getId(),
                    projetoSalvo.getNome(),
                    projetoSalvo.getObjetivo(),
                    projetoSalvo.getUnidade(),
                    projetoSalvo.getDataInicio(),
                    projetoSalvo.getDataFim(),
                    projetoSalvo.getPrazo(),
                    projetoSalvo.getTipo(),
                    projetoSalvo.getStatus()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(projetoDTO);

        } catch (Exception ex) {
            ex.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDTO> buscarPorId(@PathVariable Long id) {
        Projeto projeto= service.buscarProjetoPorId(id);
        return ResponseEntity.ok(ProjetoDTO.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .unidade(projeto.getUnidade())
                //.objetivo(projeto.getObjetivo())
                .dataInicio(projeto.getDataInicio())
                //.dataFim(projeto.getDataFim())
                .prazo(projeto.getPrazo())
                //.tipo(projeto.getTipo())
                //.status(projeto.getStatus())
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoDTO> atualizar(@PathVariable Long id, @RequestBody Projeto projeto) {
        Projeto projetoAtualizado = service.salvarProjeto(id, projeto);
        return ResponseEntity.ok(ProjetoDTO.builder()
                .id(projetoAtualizado.getId())
                .nome(projetoAtualizado.getNome())
                .unidade(projeto.getUnidade())
                //.objetivo(projetoAtualizado.getObjetivo())
                .dataInicio(projetoAtualizado.getDataInicio())
                //.dataFim(projetoAtualizado.getDataFim())
                .prazo(projetoAtualizado.getPrazo())
                //.tipo(projetoAtualizado.getTipo())
                //.status(projetoAtualizado.getStatus())
                .build());
    }


}