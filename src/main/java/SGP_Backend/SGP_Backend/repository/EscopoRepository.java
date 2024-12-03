package SGP_Backend.SGP_Backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import SGP_Backend.SGP_Backend.domain.Escopo;

@Repository
public interface EscopoRepository extends JpaRepository<Escopo, String> {
	
}
