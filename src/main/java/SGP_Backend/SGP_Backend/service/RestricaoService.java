package SGP_Backend.SGP_Backend.service;

import SGP_Backend.SGP_Backend.dto.RestricaoDTO;
import SGP_Backend.SGP_Backend.exceptions.projeto.ProjetoNaoEncontradoException;
import SGP_Backend.SGP_Backend.exceptions.restricao.RestricaoNaoEncontradaException;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.model.Restricao;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import SGP_Backend.SGP_Backend.repository.RestricaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RestricaoService {
    private RestricaoRepository restricaoRepository;
    private ProjetoRepository projetoRepository;

    public List<RestricaoDTO> buscarTodasRestricoesDeUmProjeto(Long idProjeto) {
        List<Restricao> restricoes = restricaoRepository.findAllByProjetoId(idProjeto);
        return restricoes.stream()
                .map(restricao ->
                        new RestricaoDTO(restricao.getId(), restricao.getTipo(), restricao.getDescricao(), restricao.getDificuldadeGerada()))
                .toList();
    }

    public RestricaoDTO salvarRestricao(Long idProjeto, RestricaoDTO restricaoDTO) {
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new ProjetoNaoEncontradoException("Projeto não encontrado."));

        Restricao restricao = new Restricao(restricaoDTO.getTipo(), restricaoDTO.getDescricao(), restricaoDTO.getDificuldadeGerada(), projeto);
        restricao = restricaoRepository.save(restricao);

        return new RestricaoDTO(restricao.getId(), restricao.getTipo(), restricao.getDescricao(), restricao.getDificuldadeGerada());
    }

    public void deletarRestricao(Long idProjeto, Long idRestricao) {
        Restricao restricao = restricaoRepository.findById(idRestricao)
                .orElseThrow(() -> new RestricaoNaoEncontradaException("Restrição não encontrada."));
        if (!restricao.getProjeto().getId().equals(idProjeto)) {
            throw new ProjetoNaoEncontradoException("Projeto não encontrado para a restrição fornecida.");
        }
        restricaoRepository.delete(restricao);
    }

    public RestricaoDTO atualizarRestricao(Long idProjeto, Long idRestricao, RestricaoDTO restricaoDTOAtualizada) {

        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new ProjetoNaoEncontradoException("Projeto não encontrado."));

        Restricao restricao = restricaoRepository.findById(idRestricao)
                .orElseThrow(() -> new RestricaoNaoEncontradaException("Restrição não encontrada."));

        if (!restricao.getProjeto().getId().equals(idProjeto)) {
            throw new ProjetoNaoEncontradoException("Projeto não encontrado para a restrição fornecida.");
        }

        restricao.setTipo(restricaoDTOAtualizada.getTipo());
        restricao.setDescricao(restricaoDTOAtualizada.getDescricao());
        restricao.setDificuldadeGerada(restricaoDTOAtualizada.getDificuldadeGerada());
        restricao = restricaoRepository.save(restricao);

        return new RestricaoDTO(restricao.getId(), restricao.getTipo(), restricao.getDescricao(), restricao.getDificuldadeGerada());
    }
}