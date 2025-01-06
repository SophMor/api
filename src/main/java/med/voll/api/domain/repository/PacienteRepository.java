package med.voll.api.domain.repository;

import med.voll.api.domain.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
  Page<Paciente> findAllByActivoTrue(Pageable paginacion);

  @Query("""
            select p.activo
            from Paciente p
            where p.id=:idPaciente 
            """)
  Optional<Long> findActivoById(Long idPaciente);

}
