package med.voll.api.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.DatosReservaConsultar;
import med.voll.api.domain.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoOtraConsulta  implements ValidadorConsultas{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsultar datosReservaConsultar) {
        var medicoTieneConsultaMismoHorario = consultaRepository.existsByMedico_IdAndFecha(datosReservaConsultar.idMedico().getId(),datosReservaConsultar.fecha());
        if (medicoTieneConsultaMismoHorario){throw new ValidationException("medico ya tiene consulta");
        }

    }

}
