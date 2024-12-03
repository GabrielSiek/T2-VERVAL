package SGP_Backend.SGP_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import SGP_Backend.SGP_Backend.model.Justificativa;

import java.util.List;

@Repository
public interface JustificativaRepository extends JpaRepository<Justificativa, Long> {
     @Query("SELECT j FROM Justificativa j WHERE j.projeto.id = :id")
    Justificativa buscarPorProjetoId(@Param("id") Long projetoId);
}
