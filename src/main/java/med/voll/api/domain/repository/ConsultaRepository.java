package med.voll.api.domain.repository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.persistence.ConsultaTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<ConsultaTable,Long>
{
    
    boolean existsByPaciente_IdAndFechaBetween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    boolean existsByMedico_IdAndFecha(Long id,LocalDateTime fecha);

     boolean existsByPacienteAndFecha(Paciente paciente, LocalDateTime fecha);
}
