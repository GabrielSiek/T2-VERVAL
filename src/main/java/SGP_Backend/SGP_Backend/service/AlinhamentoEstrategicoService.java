package SGP_Backend.SGP_Backend.service;

import SGP_Backend.SGP_Backend.dto.AlinhamentoEstrategicoDTO;
import SGP_Backend.SGP_Backend.dto.OpcoesObjetivoDTO;
import SGP_Backend.SGP_Backend.exceptions.projeto.ProjetoNaoEncontradoException;
import SGP_Backend.SGP_Backend.model.AlinhamentoEstrategico;
import SGP_Backend.SGP_Backend.model.ObjetivoEstrategico;
import SGP_Backend.SGP_Backend.model.OpcaoAlinhamentoEstrategico;
import SGP_Backend.SGP_Backend.repository.AlinhamentoEstrategicoRepository;
import SGP_Backend.SGP_Backend.repository.ObjetivoEstrategicoRepository;
import SGP_Backend.SGP_Backend.repository.OpcaoAlinhamentoEstrategicoRepository;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AlinhamentoEstrategicoService {

    private final AlinhamentoEstrategicoRepository repository;
    private final ProjetoService projetoService;
    private final OpcaoAlinhamentoEstrategicoRepository opcoesRepository;
    private ObjetivoEstrategicoRepository objetivoEstrategicoRepository;

    public AlinhamentoEstrategico CriarAlinhamentoEstrategico(AlinhamentoEstrategicoDTO alinhamentoEstrategicoDTO) {
        AlinhamentoEstrategico alinhamentoEstrategico = AlinhamentoEstrategico.fromDTO(alinhamentoEstrategicoDTO);
        alinhamentoEstrategico.setProjeto(projetoService.buscarProjetoPorId(alinhamentoEstrategicoDTO.getProjetoId()));
        return repository.save(alinhamentoEstrategico);
    }

    public AlinhamentoEstrategico buscarAlinhamentoPorId(Long id) {
        return repository.getReferenceById(id);
    }

    public List<AlinhamentoEstrategico> buscarAlinhamentoPorProjetoId(Long id) {
        projetoService.buscarProjetoPorId(id);
        return repository.buscarPorProjetoId(id);
    }

    public AlinhamentoEstrategico atualizarAlinhamentoEstrategico(Long id, AlinhamentoEstrategico alinhamentoRequest) {
        Optional<AlinhamentoEstrategico> alinhamento = repository.findById(id);
        if (alinhamento.isEmpty()){
            throw new ProjetoNaoEncontradoException("Alinhamento estratégico não encontrado.");
        }

        alinhamentoRequest.setProjeto(alinhamento.get().getProjeto());
        return repository.save(alinhamentoRequest);
    }

    public List<OpcaoAlinhamentoEstrategico> buscarOpcoesDeAlinhamentoEstrategico() {
        return opcoesRepository.findAll();
    }

    public List<ObjetivoEstrategico> findAll() {
        return objetivoEstrategicoRepository.findAll();
    }

    public OpcoesObjetivoDTO buscarOpcoes(){
        List<ObjetivoEstrategico> objetivos = findAll();
        List<OpcaoAlinhamentoEstrategico> opcoeSimples = buscarOpcoesDeAlinhamentoEstrategico();
        return new OpcoesObjetivoDTO(opcoeSimples, objetivos);
    }
}

