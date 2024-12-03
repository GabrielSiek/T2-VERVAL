package SGP_Backend.SGP_Backend.repository;

import SGP_Backend.SGP_Backend.model.AlinhamentoEstrategico;
import SGP_Backend.SGP_Backend.model.OpcaoAlinhamentoEstrategico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OpcaoAlinhamentoEstrategicoRepository extends JpaRepository<OpcaoAlinhamentoEstrategico, Long> {
}