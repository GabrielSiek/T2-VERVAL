package SGP_Backend.SGP_Backend.repository;

import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import SGP_Backend.SGP_Backend.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("SELECT p FROM Projeto p WHERE (:tipo% IS NULL OR p.tipo = :tipo%) AND (:status% IS NULL OR p.status = :status%)")
    List<Projeto> findAllByTipoAndStatus(@Param("tipo") TipoProjeto tipo, @Param("status") StatusProjeto status);
    Projeto findById(long id);
}