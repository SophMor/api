package med.voll.api.domain.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import med.voll.api.domain.MotivoCancelamiento;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Table(name="consultas")
@Entity(name = "ConsultaTable")
@EqualsAndHashCode(of = "id")
public class ConsultaTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading por defecto
    @JoinColumn(name = "medico_id", nullable = false)
    @JsonIgnore
   private MedicoTable medico;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    private LocalDateTime fecha;


    @Column(name = "motivo_cancelamiento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamiento motivoCancelamiento;

    public void cancelar(med.voll.api.domain.consultas.@NotNull MotivoCancelamiento motivo) {
        this.motivoCancelamiento = motivoCancelamiento;
    }
    public ConsultaTable( MedicoTable medico, Paciente paciente, LocalDateTime fecha) {

        this.medico = medico;
        this.paciente = paciente;
        this.fecha = fecha;
    }
    public ConsultaTable() {

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MedicoTable getMedico() {
        return medico;
    }

    public void setMedico(MedicoTable medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}
