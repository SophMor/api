package med.voll.api.domain.persistence;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.dto.DatosDireccion;
import med.voll.api.domain.dto.DatosMedicoActualizado;
import med.voll.api.domain.medico.DatosMedico;
import med.voll.api.domain.medico.Especialidad;

@Entity
@Table(name = "medicos")
@Getter @Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MedicoTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
     @Embedded
    private DireccionTable direccion;

     private Boolean active;
    public MedicoTable(DatosMedico datosMedico) {
    try {
        this.active = true;
        this.nombre = datosMedico.nombre();
        this.email = datosMedico.email();
        this.documento = datosMedico.documento();
        this.telefono = datosMedico.telefono();
        this.especialidad = datosMedico.especialidad();
        this.direccion = new DireccionTable(datosMedico.direccion());

    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    }
    public MedicoTable(String nombre, String email, String documento, Especialidad especialidad) {

    }
    public MedicoTable() {}

    public MedicoTable(String nombre, String email, String documento, Especialidad especialidad, DatosDireccion datosDireccion) {
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public DireccionTable getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionTable direccion) {
        this.direccion = direccion;
    }

    public void actualizarDatos(DatosMedicoActualizado datosMedico) {
   if (datosMedico.nombre() != null) {
       this.nombre = datosMedico.nombre();
   }
   if (datosMedico.documento() != null) {
       this.documento = datosMedico.documento();
   }
   if (datosMedico.direccion() != null)this.direccion= direccion.actualizarDatos(datosMedico.direccion());
    }

    public void desactivarMedico(){
        this.active = false;
    }


}

