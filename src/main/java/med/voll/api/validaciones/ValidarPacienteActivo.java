package med.voll.api.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.DatosAgendarConsulta;
import med.voll.api.domain.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidarPacienteActivo  implements ValidadorConsultas {
   @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosAgendarConsulta datosReservaConsultar) {
        Optional<Long> pacienteId = pacienteRepository.findActivoById(datosReservaConsultar.idPaciente());
        if (pacienteId.isPresent()) {
            System.out.println("El paciente existe con ID: " + pacienteId.get());
        } else {
            throw new ValidationException("No existe un paciente con el ID proporcionado.");
        }
    }

    }
