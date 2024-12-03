package SGP_Backend.SGP_Backend.repository;

import SGP_Backend.SGP_Backend.model.Fase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaseRepository extends JpaRepository<Fase, Long> {
    List<Fase> findByProjetoId(Long projetoId);
}
