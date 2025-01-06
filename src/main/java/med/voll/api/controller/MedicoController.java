package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dto.DatosListadoMedico;
import med.voll.api.domain.dto.DatosMedicoActualizado;
import med.voll.api.domain.dto.DatosDireccion;
import med.voll.api.domain.medico.DatosMedico;
import med.voll.api.domain.medico.MedicoDTOResponse;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.persistence.MedicoTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")

public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<MedicoDTOResponse> registrarMedico(@RequestBody @Valid DatosMedico datosMedico
   ,UriComponentsBuilder uriComponentBuilder) {
        System.out.println("Registrado con exitooo  \n"+
                datosMedico);
     MedicoTable medico =   medicoRepository.save(new MedicoTable(datosMedico));
    /* Return 201 CREATED Y URL DE ENCONTRAR MEDICO *
     */
        MedicoDTOResponse medicoDTOResponse =new MedicoDTOResponse(

                medico.getId(),
                medico.getNombre(),
                medico.getTelefono(),
                medico.getDocumento(),
                medico.getEmail(),
                medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),medico.getDireccion().getCiudad(),medico.getDireccion().getNumero(),medico.getDireccion().getComplemento()));
        URI url = uriComponentBuilder.path("/medicos/{id}").build(medico.getId()) ;
        return ResponseEntity.created(url).body(medicoDTOResponse);}
   /* @GetMapping
    public List<MedicoTable> listarMedicos(){
        return medicoRepository.findMedicoAndOrder();
    }*/
    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> medicoTableList(@PageableDefault(size = 10) Pageable pageable) {
    var page = medicoRepository.findByActiveTrue(pageable).map(DatosListadoMedico::new);
    return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity medicoActualizar(@RequestBody @Valid DatosMedicoActualizado datosMedico) {
    MedicoTable medico = medicoRepository.getReferenceById(datosMedico.id());
    medico.actualizarDatos(datosMedico);
        System.out.println("ExITO");
    return  ResponseEntity.ok(new MedicoDTOResponse(
            medico.getId(),
            medico.getNombre(),
            medico.getTelefono(),
            medico.getDocumento(),
            medico.getEmail(),
            medico.getEspecialidad(),
            new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),medico.getDireccion().getCiudad(),medico.getDireccion().getNumero(),medico.getDireccion().getComplemento())));
    }

    //Delete Normal/*
    /*
    @DeleteMapping("/{id}")
    @Transactional
    public void medicoBorrar(@PathVariable long id) {
        try{
            //pathVariable son los que vienen del path
            MedicoTable medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
        medico.desactivarMedico();
            System.out.println("Success");
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
*/
    /*
    Delete logico
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity medicoBorrarLogico(@PathVariable long id) {
        MedicoTable medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        System.out.println("Success");
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTOResponse> returnMedicoData(@PathVariable Long id)
    {
        MedicoTable medico = medicoRepository.getReferenceById(id);
        var datosRespuesta =  (new MedicoDTOResponse(
                medico.getId(),
                medico.getNombre(),
                medico.getTelefono(),
                medico.getDocumento(),
                medico.getEmail(),
                medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),medico.getDireccion().getCiudad(),medico.getDireccion().getNumero(),medico.getDireccion().getComplemento())));;
        return ResponseEntity.ok(datosRespuesta);
    }
}

