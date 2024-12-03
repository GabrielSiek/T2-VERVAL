package SGP_Backend.SGP_Backend.repository;

import SGP_Backend.SGP_Backend.model.ObjetivoEstrategico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetivoEstrategicoRepository extends JpaRepository<ObjetivoEstrategico, Long> {
}