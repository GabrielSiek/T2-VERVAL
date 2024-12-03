package SGP_Backend.SGP_Backend.service;

import SGP_Backend.SGP_Backend.dto.PremissaDTO;
import SGP_Backend.SGP_Backend.exceptions.database.DatabaseException;
import SGP_Backend.SGP_Backend.model.Premissa;
import SGP_Backend.SGP_Backend.repository.PremissaRepository;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PremissaService {

  private final PremissaRepository premissaRepository;
  private final ProjetoRepository projetoRepository;

  public Premissa criarPremissa(PremissaDTO premissaDTO) {
    Premissa premissa = Premissa.fromDTO(premissaDTO);

    try {
      return premissaRepository.save(premissa);
    } catch (Exception e) {
      throw new DatabaseException("Erro ao salvar a premissa");
    }
  }

  public Premissa buscarPremissa(Long id) {
    return premissaRepository.findById(id)
        .orElseThrow(() -> new DatabaseException("Premissa não encontrada"));
  }

  public Premissa atualizarPremissa(Long id, PremissaDTO premissaDTO) {

    Premissa premissa = premissaRepository.findById(id)
        .orElseThrow(() -> new DatabaseException("Premissa não encontrada"));

    projetoRepository.findById(premissaDTO.getProjetoId())
        .orElseThrow(() -> new DatabaseException("Projeto não encontrado"));

    premissa.setProjeto(premissa.getProjeto());
    premissa.setDescricao(premissaDTO.getDescricao());
    premissa.setAcaoParaManutencao(premissaDTO.getAcaoParaManutencao());

    try {
      return premissaRepository.save(premissa);
    } catch (Exception e) {
      throw new DatabaseException("Erro ao atualizar a premissa");
    }
  }
}
