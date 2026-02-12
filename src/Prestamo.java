import java.time.LocalDate;

public class Prestamo {

  private String codigoLibro;
  private String tituloLibro;
  private Usuario socio;
  private LocalDate fechaPrestamo;
  private LocalDate fechaDevolucionPrevista;
  private LocalDate fechaDevolucionReal;



  public Prestamo(String codigoLibro, Usuario socio, String tituloLibro, LocalDate fechaPrestamo)
    throws PrestamoInvalidoException{

    if (codigoLibro == null || !codigoLibro.matches("^[A-Z]{3}\\d{4}$")) {
      throw new PrestamoInvalidoException("Código de libro incorrecto. Debe ser 3 mayúsculas y 4 números.");
    }

    if (tituloLibro == null || tituloLibro.trim().isEmpty()) {
      throw new PrestamoInvalidoException("El título del libro no puede estar vacío.");
    }


    this.codigoLibro = codigoLibro;
    this.socio = socio;
    this.tituloLibro = tituloLibro;
    this.fechaPrestamo = fechaPrestamo;

  }





}
