package med.voll.api.domain.repository;

import med.voll.api.domain.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
  //  Page<Paciente> findByDocumento(Pageable pageable);
  Page<Paciente> findAllByDocumentoIsNotNull(Pageable paginacion);

  @Query("select m.id from Paciente m where m.id = :id")
  Optional<Long> findPacienteIdById( Long id);

}
