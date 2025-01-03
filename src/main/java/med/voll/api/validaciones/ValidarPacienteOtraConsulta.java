package med.voll.api.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.DatosReservaConsultar;
import med.voll.api.domain.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteOtraConsulta  implements ValidadorConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsultar datosReservaConsultar) {
    var primerHorario = datosReservaConsultar.fecha().withHour(7);
    var ultimoHorario = datosReservaConsultar.fecha().withHour(18);
    var pacienteConsultaSameDia = consultaRepository.existsByPaciente_IdAndFechaBetween(datosReservaConsultar.idPaciente().getId(),primerHorario,ultimoHorario);
    if (pacienteConsultaSameDia){
        throw new ValidationException("Sr. Paciente busque una fecha de consulta diferente");
    }
    }

}
