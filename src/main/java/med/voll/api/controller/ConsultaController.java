package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.DatosReservaConsultar;
import med.voll.api.domain.consultas.ReservaDeConsultas;
import med.voll.api.domain.persistence.ConsultaTable;
import med.voll.api.domain.persistence.MedicoTable;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.validaciones.ValidadorConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ReservaDeConsultas reserva;

    @PostMapping
    @Transactional

    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsultar datos) {

        var detalleConsulta = reserva.reservar(datos);

        return ResponseEntity.ok(detalleConsulta);
    }
}
/*
    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!repository.existsById(datos.idConsulta())) {
            throw new ValidationException("Id de la consulta informado no existe!");
        }
        var consulta = repository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarCita(@RequestBody @Valid DatosCancelamientoConsulta datos) {
        cancelar(datos);
        return ResponseEntity.noContent().build();
    }

*/