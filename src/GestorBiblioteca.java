import java.time.LocalDate;

public class GestorBiblioteca {

  private static final int MAX_USUARIOS = 50;
  private static final int MAX_PRESTAMOS = 200;
  private Usuario[] usuarios;
  private Prestamo[] prestamos;
  private int numeroUsuarios;
  private int numeroPrestamos;


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


  public Prestamo realizarPrestamo(String codigoLibro, String titulo, LocalDate fecha, Usuario usuario)
    throws PrestamoInvalidoException, UsuarioSancionadoException, LibroNoDisponibleException {

    if (usuario.estaSancionado()) {

      throw new UsuarioSancionadoException("El usuario con número de socio: "+ usuario.getNumeroSocio() +" está sancionado hasta el " + usuario.getFechaFinSancion());

    }


    for (int i = 0; i < numeroPrestamos; i++) {

      if (prestamos[i].getCodigoLibro().equals(codigoLibro) && prestamos[i].getFechaDevolucionReal() == null) {
        throw new LibroNoDisponibleException("El libro con código: " + codigoLibro + " ya se encuentra prestado.");

      }

    }


    Prestamo nuevoPrestamo = new Prestamo(codigoLibro, usuario , titulo, fecha);
    if (numeroPrestamos < MAX_PRESTAMOS) {

      prestamos[numeroPrestamos] = nuevoPrestamo;
      numeroPrestamos++;

      return nuevoPrestamo;
    }

    return null;

  }



  }



}
