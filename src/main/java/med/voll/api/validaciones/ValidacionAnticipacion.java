package med.voll.api.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component

public class ValidacionAnticipacion implements ValidadorConsultas
{
    public void validar(DatosAgendarConsulta datosReservaConsultar){
        var fecha = datosReservaConsultar.fecha();
        var actual = LocalDateTime.now();
        var diferencia = Duration.between(actual, fecha).toMinutes();
        if (diferencia<30){
            throw new ValidationException("Asegurese que el horario sea mayor a 30 minutos");
        }

    }
}
