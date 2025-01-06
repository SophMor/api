package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.*;
import med.voll.api.domain.persistence.ConsultaTable;
import med.voll.api.domain.persistence.MedicoTable;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.validaciones.ValidadorConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/consultas")

@SuppressWarnings("all")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService service;

    @GetMapping
    public ResponseEntity<Page<DatosDetalleConsulta>> listar(@PageableDefault(size = 10, sort = {"fecha"}) Pageable paginacion) {
        var response = service.consultar(paginacion);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos) throws ValidationException {
        var response = service.agendar(datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos) {
        service.cancelar(datos);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/relatorio-mensual/{mes}")
    public ResponseEntity<List<DatosRelatoriosConsultaMensual>> generarRelatorioConsultaMensual(@PathVariable YearMonth mes) {
        //var relatorio = consultaRepository.?
        return ResponseEntity.ok(null);
    }
}