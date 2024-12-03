package SGP_Backend.SGP_Backend.service;

import SGP_Backend.SGP_Backend.dto.RiscoDTO;
import SGP_Backend.SGP_Backend.exceptions.projeto.ProjetoNaoEncontradoException;
import SGP_Backend.SGP_Backend.exceptions.risco.RiscoInvalidoException;
import SGP_Backend.SGP_Backend.model.Risco;
import SGP_Backend.SGP_Backend.repository.RiscoRepository;
import SGP_Backend.SGP_Backend.enuns.Nivel;
import SGP_Backend.SGP_Backend.enuns.RespostaRisco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RiscoService {

    private final RiscoRepository repository;

    public Risco criarRisco(RiscoDTO riscoDTO) {
        Risco risco = Risco.fromDTO(riscoDTO);

        return repository.save(risco);
    }

    public List<Risco> buscarRiscoPorProjetoId(Long projetoId) {
        return repository.buscarPorProjetoId(projetoId);
    }

    public Risco atualizarRisco(Long id, Risco riscoRequest) {
        Optional<Risco> riscoOptional = repository.findById(id);

        if (riscoOptional.isEmpty()) {
            throw new ProjetoNaoEncontradoException("Risco não encontrado.");
        }

        Risco risco = riscoOptional.get();
        Nivel nivel = riscoRequest.getNivel();
        RespostaRisco resposta = riscoRequest.getResposta();

        if (!nivel.equals(Nivel.BAIXO) && resposta.equals(RespostaRisco.ACEITAR)) {
            throw new RiscoInvalidoException(
                    "Resposta não pode ser 'ACEITAR' para riscos de nível médio, alto ou muito alto."
            );
        }

        risco.setNivel(riscoRequest.getNivel());
        risco.setResposta(riscoRequest.getResposta());
        risco.setDescricao(riscoRequest.getDescricao());
        risco.setProjeto(riscoRequest.getProjeto());

        return repository.save(risco);
    }
}
