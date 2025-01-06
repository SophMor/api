package med.voll.api.domain.persistence;

import jakarta.persistence.Embeddable;
import med.voll.api.domain.dto.DatosDireccion;

@Embeddable

public class DireccionTable {
 private String calle;
    private String distrito;
    private String ciudad;
    private  String numero;
    private String complemento;

    public DireccionTable(DatosDireccion direccion) {
    this.calle = direccion.calle();
    this.distrito = direccion.distrito();
    this.ciudad = direccion.ciudad();
    this.numero = direccion.numero();
    this.complemento = direccion.complemento();
    }

    //en caso error lombook
    public DireccionTable() {
    }

   public DireccionTable(String calle, String distrito, String ciudad, String numero, String complemento) {
      this.calle = calle;
      this.distrito = distrito;
      this.ciudad = ciudad;
      this.numero = numero;
      this.complemento = complemento;
   }

    public DireccionTable(DireccionTable direccion) {
    }

    public DireccionTable actualizarDatos(DatosDireccion direccion) {
   this.calle = direccion.calle();
   this.distrito = direccion.distrito();
   this.ciudad = direccion.ciudad();
   this.numero = direccion.numero();
   this.complemento = direccion.complemento();
   return this;
   }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }


}
