import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Prestamo {

  private String codigoLibro;
  private String tituloLibro;
  private Usuario socio;
  private LocalDate fechaPrestamo;
  private LocalDate fechaDevolucionPrevista;
  private LocalDate fechaDevolucionReal;

  private static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");


  public Prestamo(String codigoLibro, String tituloLibro, Usuario socio, LocalDate fechaPrestamo)
    throws PrestamoInvalidoException{

    if (codigoLibro == null || !codigoLibro.matches("^[A-Z]{3}\\d{4}$")) {
      throw new PrestamoInvalidoException("Código de libro incorrecto. Debe ser 3 mayúsculas y 4 números.");
    }

    if (tituloLibro == null || tituloLibro.trim().isEmpty()) {
      throw new PrestamoInvalidoException("El título del libro no puede estar vacío.");
    }

    if (fechaPrestamo == null || fechaPrestamo.isAfter(LocalDate.now())) {
      throw new PrestamoInvalidoException("La fecha de préstamo no es válida.");
    }

    this.codigoLibro = codigoLibro;
    this.socio = socio;
    this.tituloLibro = tituloLibro;
    this.fechaPrestamo = fechaPrestamo;
    this.fechaDevolucionPrevista = fechaPrestamo.plusDays(14);
    this.fechaDevolucionReal = null;

  }


  public void registrarDevolucion(LocalDate fecha)
    throws PrestamoInvalidoException {

    if (fecha == null || fecha.isBefore(this.fechaPrestamo)) {
      throw new PrestamoInvalidoException("Fecha de devolución no válida.");
    }

    this.fechaDevolucionReal = fecha;

  }


  public int calcularDiasRetraso() {

    if (fechaDevolucionReal == null) {

      long dias = ChronoUnit.DAYS.between(fechaDevolucionPrevista, LocalDate.now());

      if (dias > 0) {
        return (int) dias;
      } else {
        return 0; }

    } else {

      long dias = ChronoUnit.DAYS.between(fechaDevolucionPrevista, fechaDevolucionReal);

      if (dias > 0) {
        return (int) dias;
      } else {
        return 0;
      }
    }

  }


  public boolean estaRetrasado() {

    if (fechaDevolucionReal == null) {
      return LocalDate.now().isAfter(fechaDevolucionPrevista);
    }
    return fechaDevolucionReal.isAfter(fechaDevolucionPrevista);
  }


  @Override
  public String toString() {

      return "\n" + "---Préstamo--- "+ "\n"
        +"Título: " + tituloLibro + "\n"
        + "Código del libro: " + codigoLibro + "\n"
        +"Número de socio: " + socio.getNumeroSocio() + "\n"
        +"Fecha del préstamo: " + fechaPrestamo + "\n" + "\n"
        +"Prestamo Realizado." + "\n"
        +"Devolución prevista: " + fechaDevolucionPrevista.format(formato);

    }







  public String getCodigoLibro() {
    return codigoLibro;
  }

  public LocalDate getFechaDevolucionReal() {
    return fechaDevolucionReal;
  }

  public LocalDate getFechaDevolucionPrevista() {
    return fechaDevolucionPrevista;
  }

  public Usuario getSocio() {
    return socio;
  }

}
