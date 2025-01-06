package med.voll.api.validaciones;

import med.voll.api.domain.consultas.DatosAgendarConsulta;
import med.voll.api.domain.medico.Especialidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.DayOfWeek;
import jakarta.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

class ValidacionFueraHorarioTest {

    private ValidaacionFueraHorario validador;

    @BeforeEach
    void setUp() {
        // Inicialización del validador antes de cada prueba
        validador = new ValidaacionFueraHorario();
    }

    @Test
    void testConsultaEnDomingo() {
        // Caso: Consulta en Domingo
        LocalDateTime fechaConsultaDomingo = LocalDateTime.of(2025, Month.JANUARY, 5, 10, 0);  // Domingo
        DatosAgendarConsulta datosReservaConsulta = new DatosAgendarConsulta(1L, 1L, fechaConsultaDomingo, Especialidad.PEDIATRIA);

        // Verificar que se lance una excepción
        assertThrows(ValidationException.class, () -> validador.validar(datosReservaConsulta), "Horario fuera de servicio");
    }

    @Test
    void testConsultaAntesDeLas7AM() {
        // Caso: Consulta antes de las 7 AM
        LocalDateTime fechaConsultaAntesDeLas7AM = LocalDateTime.of(2025, Month.JANUARY, 6, 6, 30);  // Antes de las 7 AM
        DatosAgendarConsulta datosReservaConsulta = new DatosAgendarConsulta(1L, 1L, fechaConsultaAntesDeLas7AM,Especialidad.PEDIATRIA);

        // Verificar que se lance una excepción
        assertThrows(ValidationException.class, () -> validador.validar(datosReservaConsulta), "Horario fuera de servicio");
    }

    @Test
    void testConsultaDespuesDeLas6PM() {
        // Caso: Consulta después de las 6 PM
        LocalDateTime fechaConsultaDespuesDeLas6PM = LocalDateTime.of(2025, Month.JANUARY, 6, 19, 30);  // Después de las 6 PM
        DatosAgendarConsulta datosReservaConsulta = new DatosAgendarConsulta(1L, 1L, fechaConsultaDespuesDeLas6PM, Especialidad.PEDIATRIA);

        // Verificar que se lance una excepción
        assertThrows(ValidationException.class, () -> validador.validar(datosReservaConsulta), "Horario fuera de servicio");
    }

    @Test
    void testConsultaDentroDelHorario() {
        // Caso: Consulta dentro del horario permitido (lunes, entre las 7 AM y las 6 PM)
        LocalDateTime fechaConsultaValida = LocalDateTime.of(2025, Month.JANUARY, 6, 10, 0);  // Lunes a las 10 AM
        DatosAgendarConsulta datosReservaConsulta = new DatosAgendarConsulta(1L, 1L, fechaConsultaValida, Especialidad.PEDIATRIA);

        // No debería lanzar ninguna excepción
        assertDoesNotThrow(() -> validador.validar(datosReservaConsulta));
    }
}
