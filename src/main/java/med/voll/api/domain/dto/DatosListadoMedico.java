package med.voll.api.domain.dto;

import med.voll.api.domain.persistence.MedicoTable;

public record DatosListadoMedico(
       Long id,
        String nombre,
        String email,
        String documento,
        String especialidad
        )
{
    public DatosListadoMedico(MedicoTable medico) {
        this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
    }
}
