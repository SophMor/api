package med.voll.api.domain.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.DatosDireccion;

public record DatosMedicoActualizado(
        @NotNull
        Long id,
        String nombre,
        String documento,
        DatosDireccion direccion
)
{

}

