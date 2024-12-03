package SGP_Backend.SGP_Backend.controller;

import SGP_Backend.SGP_Backend.dto.RestricaoDTO;
import SGP_Backend.SGP_Backend.service.RestricaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restricoes")
@RequiredArgsConstructor
public class RestricaoController {
    private final RestricaoService restricaoService;

    @GetMapping("/projeto")
    public List<RestricaoDTO> buscarRestricaoPorProjetoId(@RequestParam Long idProjeto){
        return restricaoService.buscarTodasRestricoesDeUmProjeto(idProjeto);
    }

    @PostMapping
    public RestricaoDTO salvarRestricao(@RequestParam Long idProjeto, @RequestBody RestricaoDTO restricao){
        return restricaoService.salvarRestricao(idProjeto, restricao);
    }

    @DeleteMapping
    public void deletarRestricao(Long idProjeto, Long idRestricao){
        restricaoService.deletarRestricao(idProjeto, idRestricao);
    }

    @PutMapping
    public RestricaoDTO atualizarRestricao(@RequestParam Long idProjeto, @RequestParam Long idRestricao, @RequestBody RestricaoDTO restricaoDTOAtualizada){
        return restricaoService.atualizarRestricao(idProjeto, idRestricao, restricaoDTOAtualizada);
    }
}
