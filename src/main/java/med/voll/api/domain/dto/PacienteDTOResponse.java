package med.voll.api.domain.dto;

public record PacienteDTOResponse(
        Long id, String nombre,
        String email,
        String documento,
        String telefono
) {

}
