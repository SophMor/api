package med.voll.api.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.DatosAgendarConsulta;
import med.voll.api.domain.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoActivo  implements ValidadorConsultas{
    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosAgendarConsulta datosReservaConsultar) {
    if (datosReservaConsultar.idMedico() == null) {
        return;
    }
    var medicoActivo = medicoRepository.findActiveById(datosReservaConsultar.idMedico());
    if (!medicoActivo) {
        throw new ValidationException("Medico no activo");
    }

    }

}
