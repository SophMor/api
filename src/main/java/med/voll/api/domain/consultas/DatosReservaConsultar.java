package med.voll.api.domain.consultas;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidad;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.persistence.MedicoTable;

import java.time.LocalDateTime;

public record DatosReservaConsultar(
        MedicoTable medico,
        Paciente paciente,
        @NotNull @Future LocalDateTime fecha,
        Especialidad especialidad
) {
        public MedicoTable idMedico() {
                return medico;
        }

        public Paciente idPaciente() {
                return paciente;
        }
}

