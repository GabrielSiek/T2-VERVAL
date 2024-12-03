package SGP_Backend.SGP_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import SGP_Backend.SGP_Backend.model.Beneficio;

import java.util.List;

@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long> {
    @Query("SELECT b FROM Beneficio b WHERE b.projeto.id = :id")
    List<Beneficio> buscarPorProjetoId(@Param("id") Long id);

    @Query("UPDATE Beneficio b SET b.beneficios = :beneficios WHERE b.projeto.id = :id")
    List<Beneficio> atualizarPorProjetoId(@Param("id") Long id);
}
