package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.domain.dto.DatosDireccion;


public record DatosRegistroPaciente(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 0, max = 15)
        String telefono,

        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}", message = "El formato del DNI debe ser XX.XXX.XXX")
        @NotBlank
        String documento,

        @NotNull @Valid DatosDireccion direccion) {
}
