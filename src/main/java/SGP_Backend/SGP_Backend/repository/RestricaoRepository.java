package SGP_Backend.SGP_Backend.repository;

import SGP_Backend.SGP_Backend.model.Restricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestricaoRepository extends JpaRepository<Restricao, Long> {
    List<Restricao> findAllByProjetoId(Long idProjeto);
}
