package med.voll.api.domain.paciente;


import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dto.DatosDireccion;

public record DatosActualizacionPaciente(
        @NotNull
        Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion) {
}
