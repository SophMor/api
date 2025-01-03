package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import med.voll.api.domain.DatosDireccion;
import med.voll.api.domain.persistence.DireccionTable;

@Table(name = "pacientes")
@Entity(name = "Paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String documento;
    private String telefono;

    @Embedded
    private DireccionTable direccion;

    public Paciente() {
    }

    public Paciente(Long id, String nombre, String email, String documento, String telefono, DireccionTable direccion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.documento = documento;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Paciente(@Valid Paciente datos) {
        this.nombre = datos.getNombre();
        this.email = datos.getEmail();
        this.documento = datos.getDocumento();
        this.telefono = datos.getTelefono();
        this.direccion = new DireccionTable(datos.getDireccion());
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

    public void setNombre(String nome) {
        this.nombre = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public DireccionTable getDireccion() {
        return direccion;
    }


    public void setDireccion(DireccionTable direccion) {
        this.direccion = direccion;
    }

    public void actualizarInformaciones(Paciente datos) {
        if (datos.getNombre() != null) {
            this.nombre = datos.getNombre();
        }
        if (datos.getTelefono() != null) {
            this.telefono = datos.getTelefono();
        }
        if (datos.getDireccion() != null)this.direccion= direccion.actualizarDatos(new DatosDireccion(getDireccion().getCiudad(), getDireccion().getDistrito(), getDireccion().getCalle(), getDireccion().getNumero(),getDireccion().getComplemento()));

    }
}
