package med.voll.api.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidaacionFueraHorario implements ValidadorConsultas{
public void validar(DatosAgendarConsulta datosReservaConsultar){
    var fechaConsulta = datosReservaConsultar.fecha();
    var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    var horarioAntesApertura = fechaConsulta.getHour() < 7;
    var horarioAfterHour = fechaConsulta.getHour() > 18 ;
    if(domingo|| horarioAfterHour||horarioAntesApertura){
        throw new ValidationException("Horario fuera de servicio");
    }
}
}
