package med.voll.api.domain.repository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consultas.DatosRelatoriosConsultaMensual;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.persistence.ConsultaTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<ConsultaTable,Long>
{
    
    boolean existsByPaciente_IdAndFechaBetween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    boolean existsByMedico_IdAndFecha(Long id,LocalDateTime fecha);

     boolean existsByPacienteAndFecha(Paciente paciente, LocalDateTime fecha);

    @Query("""
    SELECT new med.voll.api.domain.consultas.DatosRelatoriosConsultaMensual(c.medico.nombre, COUNT(c))
    FROM ConsultaTable c
    WHERE FUNCTION('YEAR', c.fecha) = :anio AND FUNCTION('MONTH', c.fecha) = :mes
    GROUP BY c.medico.nombre
""")
    List<DatosRelatoriosConsultaMensual> generarRelatorioMensual( int anio, int mes);

}
