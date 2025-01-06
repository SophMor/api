package med.voll.api.domain.paciente;

import med.voll.api.domain.persistence.DireccionTable;

public record DatosDetallesPaciente(Long id, String nombre, String email, String documento, String telefono, DireccionTable direccion) {

    public DatosDetallesPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento(), paciente.getTelefono(),paciente.getDireccion() );
    }
}