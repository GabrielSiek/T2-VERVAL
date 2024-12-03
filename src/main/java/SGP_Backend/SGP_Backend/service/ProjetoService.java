package SGP_Backend.SGP_Backend.service;

import SGP_Backend.SGP_Backend.dto.ProjetoDTO;
import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import SGP_Backend.SGP_Backend.exceptions.CorpoVazioException;
import SGP_Backend.SGP_Backend.exceptions.database.DatabaseException;
import SGP_Backend.SGP_Backend.exceptions.projeto.ProjetoNaoEncontradoException;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjetoService {

    private final ProjetoRepository repository;

    public List<ProjetoDTO> buscarTodosProjetosPorStatusETipo(TipoProjeto tipo, StatusProjeto status) {
        List<Projeto> projetos = repository.findAllByTipoAndStatus(tipo, status);
        return ProjetoDTO.converteProjetos(projetos);
    }
    public Projeto buscarProjetoPorId(Long id) {
        try {
            return repository.findById(id).get();
        }catch (Exception e) {
            throw new ProjetoNaoEncontradoException("Projeto não encontrado.");
        }
    }
    public Projeto salvarProjeto(Long id, Projeto projetoRequest) {
        Optional<Projeto> projeto = repository.findById(id);
        if (projeto.isEmpty()){
            throw new ProjetoNaoEncontradoException("Projeto não encontrado.");
        }
        return repository.save(projetoRequest);
    }

    public Projeto criarProjeto(ProjetoDTO projetoDTO){
        if (projetoDTO == null) {
            throw new CorpoVazioException("O projeto não pode ser nulo");
        }
        Projeto projeto = ProjetoDTO.criarProjetoFromDTO(projetoDTO);
        try {
            return repository.save(projeto);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao salvar o projeto");
        }
    }
}