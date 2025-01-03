package med.voll.api.domain.consultas;


import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import med.voll.api.domain.persistence.ConsultaTable;
import med.voll.api.domain.persistence.MedicoTable;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.validaciones.ValidadorConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<ValidadorConsultas> validadores;

    public DatosDetalleConsulta reservar(DatosReservaConsultar datos) {

        var medico = datos.medico();
        if (medico == null || medico.getId() == null) {
            medico = elegirMetodo(datos);
            if (medico == null) {
                throw new ValidationException("Médico no encontrado");
            }
        } else {
            validadores.forEach(v -> v.validar(datos));
        }

        var paciente = pacienteRepository.findById(datos.idPaciente().getId());
        var consulta = new ConsultaTable(medico, paciente.get(), datos.fecha());
        consultaRepository.save(consulta);

        System.out.println(consulta + " fue exitosa ");
        return new DatosDetalleConsulta(consulta);
    }




    private MedicoTable elegirMetodo(@Valid DatosReservaConsultar data) {
        if (data.idMedico() == null) {
            var medicoFound = medicoRepository.elegirMedicoAleatorioEnFecha(data.especialidad(), data.fecha());

            if (medicoFound == null) {
                throw new ValidationException("No se ha encontrado un médico disponible para la especialidad y fecha especificadas");
            }

            var pacienteExistente = consultaRepository.existsByPacienteAndFecha(data.idPaciente(), data.fecha());
            if (pacienteExistente) {
                throw new ValidationException("El paciente ya tiene una cita en esta fecha y hora.");
            }

            return medicoRepository.save(medicoFound);
        }
        if (data.especialidad() == null) {
            throw new ValidationException("Es necesario elegir una especialidad");
        }

        MedicoTable medico = medicoRepository.findById(data.idMedico().getId())
                .orElseThrow(() -> new ValidationException("Médico no encontrado"));

        return medico;
    }


}

