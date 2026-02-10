import java.time.LocalDate;
import java.util.InputMismatchException;

public class Usuario {

  private String nombre;
  private String email;
  private String numeroSocio;
  private LocalDate fechaRegistro;
  private boolean sancionado;
  private LocalDate fechaFinSancion;





  public Usuario(String nombre, String email, String numeroSocio, LocalDate fechaRegistro) {
    this.nombre = nombre;
    this.email = email;
    this.numeroSocio = numeroSocio;
    this.fechaRegistro = fechaRegistro;
  }


  public void sancionar(int diasSancion, LocalDate fechaInicio) {
    this.sancionado = true;
    this.fechaFinSancion = fechaInicio.plusDays(diasSancion);
  }


  public void levantarSancion() {
    this.sancionado = false;
    this.fechaFinSancion = null;
  }

  public boolean estaSancionado() {
    return this.sancionado;
  }


  @Override
  public String toString() {
    return "Usuario{" +
      "nombre='" + nombre + '\'' +
      ", email='" + email + '\'' +
      ", numeroSocio='" + numeroSocio + '\'' +
      ", fechaRegistro=" + fechaRegistro +
      ", sancionado=" + sancionado +
      ", fechaFinSancion=" + (fechaFinSancion != null ? fechaFinSancion : "N/A") +
      '}';
  }

  public String getNumeroSocio() {
    return numeroSocio;
  }

  public LocalDate getFechaFinSancion() {
    return fechaFinSancion;
  }




}
