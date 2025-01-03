package med.voll.api.domain.consultas;

import med.voll.api.domain.persistence.ConsultaTable;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record DatosDetalleConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime fecha) {
    public DatosDetalleConsulta(ConsultaTable consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getFecha());
    }}

