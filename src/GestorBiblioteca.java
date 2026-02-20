import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestorBiblioteca {

  private static final int MAX_USUARIOS = 50;
  private static final int MAX_PRESTAMOS = 200;
  private Usuario[] usuarios;
  private Prestamo[] prestamos;
  private int numeroUsuarios;
  private int numeroPrestamos;

  private static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public GestorBiblioteca() {
    this.usuarios = new Usuario[MAX_USUARIOS];
    this.prestamos = new Prestamo[MAX_PRESTAMOS];
    this.numeroUsuarios = 0;
    this.numeroPrestamos = 0;
  }

  public void registrarUsuario(Usuario nuevoUsuario)
    throws UsuarioRepetidoException, UsuarioInvalidoException {

    for (int i = 0; i < numeroUsuarios; i++) {

      if (usuarios[i].getNumeroSocio().equals(nuevoUsuario.getNumeroSocio())) {

        throw new UsuarioRepetidoException("El usuario con número de socio: " + nuevoUsuario.getNumeroSocio() + " ya existe.");

      }

    }

    if (numeroUsuarios < MAX_USUARIOS) {

      usuarios[numeroUsuarios] = nuevoUsuario;
      numeroUsuarios++;

    } else {

      throw new UsuarioInvalidoException("Número de usuarios completo, vuelva a intentarlo en un futuro");

    }

  }


  public Prestamo realizarPrestamo(String codigoLibro, String titulo, LocalDate fechaPrestamo, Usuario usuario)
    throws PrestamoInvalidoException, UsuarioSancionadoException, LibroNoDisponibleException {

    if (usuario.estaSancionado()) {

      throw new UsuarioSancionadoException("El usuario con número de socio: "+ usuario.getNumeroSocio() +" está sancionado hasta el " + usuario.getFechaFinSancion());

    }


    for (int i = 0; i < numeroPrestamos; i++) {

      if (prestamos[i].getCodigoLibro().equals(codigoLibro) && prestamos[i].getFechaDevolucionReal() == null) {
        throw new LibroNoDisponibleException("El libro con código: " + codigoLibro + " ya se encuentra prestado.");

      }

    }


    Prestamo nuevoPrestamo = new Prestamo(codigoLibro, titulo, usuario, fechaPrestamo);

    if (numeroPrestamos < MAX_PRESTAMOS) {

      prestamos[numeroPrestamos] = nuevoPrestamo;
      numeroPrestamos++;

      return nuevoPrestamo;

  } else {

      throw new PrestamoInvalidoException("No se pueden realizar más préstamos");

    }

  }


  public boolean devolverLibro(String codigoLibro, LocalDate fechaDevolucion)
    throws PrestamoInvalidoException {

      for (int i = 0; i < numeroPrestamos; i++) {

        if (prestamos[i].getCodigoLibro().equals(codigoLibro) && prestamos[i].getFechaDevolucionReal() == null) {

          prestamos[i].registrarDevolucion(fechaDevolucion);


          int diasRetraso = prestamos[i].calcularDiasRetraso();

          if (diasRetraso > 0) {

            prestamos[i].getSocio().sancionar(diasRetraso, fechaDevolucion);

            System.out.println();
            System.out.println("Devolución registrada con " + diasRetraso + " días de retraso");
            System.out.println("Usuario sancionado por " + diasRetraso + " días de retraso (hasta el " + fechaDevolucion.plusDays(diasRetraso).format(formato) + ")");

          }

          return true;

        }
      }

      return false;

  }


  public Usuario buscarUsuario(String numeroSocio) {

    for (int i = 0; i < numeroUsuarios; i++) {

      if (usuarios[i].getNumeroSocio().equalsIgnoreCase(numeroSocio)) {

        return usuarios[i];

      }
    }

    return null;
  }


  public Prestamo[] getPrestamos() {
    return prestamos;
  }

  public Usuario[] getUsuarios() {
    return usuarios;
  }

  public int getNumeroUsuarios() {
    return numeroUsuarios;
  }

  public int getNumeroPrestamos() {
    return numeroPrestamos;
  }


  @Override
  public String toString() {
    return "Existen " + numeroUsuarios + " usuarios registrados y " + numeroPrestamos + " préstamos realizados.";
  }


}
