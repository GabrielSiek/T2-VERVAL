package SGP_Backend.SGP_Backend.repository;

import SGP_Backend.SGP_Backend.model.Risco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiscoRepository extends JpaRepository<Risco, Long> {
    @Query("SELECT r FROM Risco r WHERE r.projeto.id = :id")
    List<Risco> buscarPorProjetoId(@Param("id") Long id);

//pensando aqui
// @Query("UPDATE Risco r SET  WHERE b.projeto.id = :id")
//    List<Risco> atualizarPorProjetoId(@Param("id") Long id);

}