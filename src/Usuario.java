import java.time.LocalDate;

public class Usuario {

  private String nombre;
  private String email;
  private String numeroSocio;
  private LocalDate fechaRegistro;
  private boolean sancionado;
  private LocalDate fechaFinSancion;





  public Usuario(String nombre, String email, String numeroSocio, LocalDate fechaRegistro)
    throws UsuarioInvalidoException {

      if (nombre == null || nombre.trim().isEmpty()) {
        throw new UsuarioInvalidoException("El nombre no puede estar vacío.");
      }

      if (email == null || email.trim().isEmpty()) {
        throw new UsuarioInvalidoException("El email no puede estar vacío.");
      }

    if (!email.contains("@") || !email.contains(".")) {
      throw new UsuarioInvalidoException("El email debe contener '@' y '.' Ejemplo: usuario@gmail.com");
    }

    if (numeroSocio == null || numeroSocio.trim().isEmpty()) {
        throw new UsuarioInvalidoException("El número de socio no puede estar vacío.");
      }

    if (!numeroSocio.matches("^SOC\\d{5}$")) {
      throw new UsuarioInvalidoException("El formato del número de socio debe ser 'SOC' seguido de 5 dígitos. Ejemplo: SOC12345");
    }

      if (fechaRegistro == null || fechaRegistro.isAfter(LocalDate.now())) {
        throw new UsuarioInvalidoException("La fecha de registro no es válida.");
      }

      this.nombre = nombre;
      this.email = email;
      this.numeroSocio = numeroSocio;
      this.fechaRegistro = fechaRegistro;
      this.sancionado = false;
      this.fechaFinSancion = null;
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
    return "---Usuario---" +"\n"
      + "Nombre: " + this.nombre + "\n"
      + "Email: " + this.email + "\n"
      + "NumeroSocio: " + this.numeroSocio + "\n"
      + "Fecha de Registro: " + this.fechaRegistro;
  }

  public String getNumeroSocio() {
    return numeroSocio;
  }

  public LocalDate getFechaFinSancion() {
    return fechaFinSancion;
  }

}
