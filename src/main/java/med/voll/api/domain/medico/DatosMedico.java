package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.dto.DatosDireccion;

//Ojo es importante que el json se nombre igual que
//los parametros
public record DatosMedico(
        @NotBlank(message = "Nombre es obligatorio")
        String nombre,

        @NotBlank(message = "Email es obligatorio")
        @Email(message = "Formato de email es inválido")
        String email,
        @NotBlank
                @Pattern(regexp = "\\d{4,6}")
        String documento,

        String telefono,
        @NotNull
        Especialidad especialidad,
        @NotNull(message = "Datos de dirección son obligatorios")
        @Valid
        DatosDireccion direccion
        ) {

}
