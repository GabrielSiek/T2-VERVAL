package SGP_Backend.SGP_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import SGP_Backend.SGP_Backend.model.Premissa;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface PremissaRepository extends JpaRepository<Premissa, Long> {

}
