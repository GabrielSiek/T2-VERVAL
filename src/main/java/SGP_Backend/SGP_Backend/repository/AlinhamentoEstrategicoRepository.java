package SGP_Backend.SGP_Backend.repository;

import SGP_Backend.SGP_Backend.model.AlinhamentoEstrategico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlinhamentoEstrategicoRepository extends JpaRepository<AlinhamentoEstrategico, Long> {
    @Query("SELECT a FROM AlinhamentoEstrategico a WHERE a.projeto.id = :id")
    List<AlinhamentoEstrategico> buscarPorProjetoId(@Param("id") Long id);

}
