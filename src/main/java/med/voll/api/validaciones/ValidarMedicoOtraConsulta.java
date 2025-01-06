package med.voll.api.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.DatosAgendarConsulta;
import med.voll.api.domain.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoOtraConsulta  implements ValidadorConsultas{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datosReservaConsultar) {
        var medicoTieneConsultaMismoHorario = consultaRepository.existsByMedico_IdAndFecha(datosReservaConsultar.idMedico(),datosReservaConsultar.fecha());
        if (medicoTieneConsultaMismoHorario){throw new ValidationException("medico ya tiene consulta");
        }

    }

}
