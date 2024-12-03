package SGP_Backend.SGP_Backend.repository;

import SGP_Backend.SGP_Backend.model.Entrega;
import SGP_Backend.SGP_Backend.model.Fase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    List<Entrega> findByFaseId(Long faseId);
}
