package med.voll.api.domain.consultas;

import jakarta.validation.ValidationException;
import med.voll.api.domain.persistence.ConsultaTable;
import med.voll.api.domain.persistence.MedicoTable;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.validaciones.ValidadorCancelamientoDeConsulta;
import med.voll.api.validaciones.ValidadorConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("all")
public class AgendaDeConsultaService {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<ValidadorConsultas> validadores;

    @Autowired(required = false)
    private List<ValidadorCancelamientoDeConsulta> validadoresCancelamiento = List.of();

    public DatosDetalleConsulta agendar(DatosAgendarConsulta datos){

        if(!pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidationException("este id para el paciente no fue encontrado");
        }

        if(datos.idMedico()!=null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidationException("este id para el medico no fue encontrado");
        }

        validadores.forEach(v-> v.validar(datos));

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        MedicoTable medico = seleccionarMedico(datos);

        if(medico==null){
            throw new ValidationException("no existen medicos disponibles para este horario y especialidad");
        }

        var consulta = new ConsultaTable(medico,paciente,datos.fecha());

        consultaRepository.save(consulta);

        return new DatosDetalleConsulta(consulta);

    }

    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidationException("Id de la consulta informado no existe!");
        }

        validadoresCancelamiento.forEach(v -> v.validar(datos));

        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }

    private MedicoTable seleccionarMedico(DatosAgendarConsulta datos) {
        if(datos.idMedico()!=null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad()==null){
            throw new ValidationException("debe seleccionarse una especialidad para el medico");
        }
        return medicoRepository.elegirMedicoAleatorioEnFecha(datos.especialidad(),datos.fecha());
    }

    public Page<DatosDetalleConsulta> consultar(Pageable paginacion) {
        return consultaRepository.findAll(paginacion).map(DatosDetalleConsulta::new);
    }

}
