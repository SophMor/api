package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.persistence.DireccionTable;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.dto.PacienteDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")

public class PacienteController {

        @Autowired
        private PacienteRepository repository;

    @PostMapping()
    public ResponseEntity<?> crearPaciente(@RequestBody Paciente paciente) {
        Paciente paciente1 = new Paciente();
        paciente1.setNombre(paciente.getNombre());
        paciente1.setEmail(paciente.getEmail());
        paciente1.setDocumento(paciente.getDocumento());
        paciente1.setTelefono(paciente.getTelefono());

        DireccionTable direccion = new DireccionTable();
        direccion.setCalle(paciente.getDireccion().getCalle());
        direccion.setDistrito(paciente.getDireccion().getDistrito());
        direccion.setCiudad(paciente.getDireccion().getCiudad());
        direccion.setNumero(paciente.getDireccion().getNumero());
        direccion.setComplemento(paciente.getDireccion().getComplemento());

        paciente1.setDireccion(direccion);

        repository.save(paciente);
        return ResponseEntity.ok("PACIENTE creado");
    }


    @GetMapping
        public ResponseEntity<Page<PacienteDTOResponse>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
            var page = repository.findAllByDocumentoIsNotNull(paginacion).map(paciente -> new PacienteDTOResponse(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento(), paciente.getTelefono()));
            return ResponseEntity.ok(page);
        }

        @PutMapping
        @Transactional
        public ResponseEntity actualizar(@RequestBody @Valid Paciente datos) {
            var paciente = repository.getReferenceById(datos.getId());
            paciente.actualizarInformaciones(datos);
       return ResponseEntity.ok(paciente);
        }

        @DeleteMapping("/{id}")
        @Transactional
        public void eliminar(@PathVariable Long id) {
            var paciente = repository.getReferenceById(id);
            repository.delete(paciente);
        }
}
