package med.voll.api.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.DatosReservaConsultar;
import med.voll.api.domain.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoActivo  implements ValidadorConsultas{
    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosReservaConsultar datosReservaConsultar) {
    if (datosReservaConsultar.idMedico() == null) {
        return;
    }
    var medicoActivo = medicoRepository.findActiveById(datosReservaConsultar.idMedico().getId());
    if (!medicoActivo) {
        throw new ValidationException("Medico no activo");
    }

    }

}
