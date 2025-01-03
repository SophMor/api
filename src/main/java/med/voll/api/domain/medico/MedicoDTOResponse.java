package med.voll.api.domain.medico;

import med.voll.api.domain.DatosDireccion;

public record MedicoDTOResponse(
        Long id,
         String nombre,
         String email,
         String telefono,
        String documento,
       Especialidad especialidad,
    DatosDireccion direccion
) {
}