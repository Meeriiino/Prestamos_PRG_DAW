import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

  private static Scanner entrada = new Scanner(System.in);

  private static GestorBiblioteca gestor = new GestorBiblioteca();

  private static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");


  public static void main(String[] args) {

    int opcion = 0;

    do {
      mostrarMenu();
      try {
        opcion = Integer.parseInt(entrada.nextLine());

        switch (opcion) {
          case 1 -> registrarUsuario();
          case 2 -> realizarPrestamo();
          case 3 -> devolverLibro();
          case 4 -> consultarEstadoUsuario();
          case 5 -> mostrarPrestamosActivos();
          case 6 -> mostrarUsuariosSancionados();
          case 7 -> actualizarSanciones();
          case 8 -> System.out.println("\n" + "Saliendo del sistema.");
          default -> System.out.println("\n" + "Opción no válida, vuelva a seleccionar una opción");
        }

      } catch (NumberFormatException e) {

        System.out.println("\n" + "ERROR: Introduce un número para elegir la opción.");
      }

    } while (opcion != 8);

    }


  private static void mostrarMenu() {

    System.out.println();
    System.out.println("=== SISTEMA GESTIÓN BIBLIOTECA ===");
    System.out.println("1. Registrar nuevo usuario");
    System.out.println("2. Realizar préstamo de libro");
    System.out.println("3. Devolver libro");
    System.out.println("4. Consultar estado de usuario");
    System.out.println("5. Mostrar préstamos activos");
    System.out.println("6. Mostrar usuarios sancionados");
    System.out.println("7. Actualizar sanciones");
    System.out.println("8. Salir");
    System.out.println();
    System.out.print("Escribe tu opción: ");
  }



  private static void registrarUsuario() {

    try{

      System.out.println("Introduce el nombre del nuevo usuario: ");
      String nombre = entrada.nextLine();

      System.out.println("Introduce el e-mail del nuevo ususario (usuario@gmail.com): ");
      String email = entrada.nextLine();

      System.out.println("Introduce el número de socio (SOC12345): ");
      String numeroSocio = entrada.nextLine();

      System.out.println("Introduce la fecha de registro (dd/mm/aaaa): ");
      LocalDate fecha = LocalDate.parse(entrada.nextLine(), formato);

      Usuario nuevo = new Usuario(nombre, email, numeroSocio, fecha);
      gestor.registrarUsuario(nuevo);

      System.out.println(nuevo.toString());
      System.out.println();
      System.out.println("Usuario correctamente registrado.");


    } catch (UsuarioInvalidoException | UsuarioRepetidoException e) {

      System.out.println();
      System.out.println("ERROR: " + e.getMessage());

    } catch (DateTimeParseException e) {

      System.out.println();
      System.out.println("ERROR: Fecha no válida, prueba con el formato (dd/mm/aaaa)");

    }

  }



  private static void realizarPrestamo() {

    try{

      System.out.println("Introduce el código del libro (AAA1234): ");
      String codigo = entrada.nextLine();

      System.out.println("Introduce el título del libro: ");
      String titulo = entrada.nextLine();

      System.out.println("Introduce el número de socio (SOC12345): ");
      String numeroSocio = entrada.nextLine();

      System.out.println("Introduce la fecha de préstamo (dd/mm/aaaa): ");
      LocalDate fecha = LocalDate.parse(entrada.nextLine(), formato);


      Usuario u = gestor.buscarUsuario(numeroSocio);

      if (u == null) {

        System.out.println();
        System.out.println("ERROR: El usuario no existe.");
        return;

      }

      Prestamo p = gestor.realizarPrestamo(codigo, titulo, fecha, u);

      System.out.println();
      System.out.println(p.toString());

    } catch (PrestamoInvalidoException | UsuarioSancionadoException | LibroNoDisponibleException e) {

      System.out.println();
      System.out.println("ERROR: " + e.getMessage());

    } catch (DateTimeParseException e) {

      System.out.println();
      System.out.println("ERROR: Fecha no válida, prueba con el formato (dd/mm/aaaa)");

    }

  }



  private static void devolverLibro() {

    try{

      System.out.println("Introduce el código del libro (AAA1234): ");
      String codigo = entrada.nextLine();

      System.out.println("Introduce la fecha de devolucion (dd/mm/aaaa): ");
      LocalDate fecha = LocalDate.parse(entrada.nextLine(), formato);


      if (gestor.devolverLibro(codigo,fecha)) {

        System.out.println();
        System.out.println("Devolución registrada correctamente.");

      }else {
        System.out.println();
        System.out.println("No se ha podido realizar la devolución, no se encontró un prestamo pendiente con ese código.");
      }


    } catch (PrestamoInvalidoException e) {

      System.out.println();
      System.out.println("ERROR: " + e.getMessage());

    } catch (DateTimeParseException e) {

      System.out.println();
      System.out.println("ERROR: Fecha no válida, prueba con el formato (dd/mm/aaaa)");

    }

  }



  private static void consultarEstadoUsuario() {

    System.out.println("Introduce el número de socio (SOC12345): ");
    String numeroSocio = entrada.nextLine();

    Usuario u = gestor.buscarUsuario(numeroSocio);

if (u != null){

  System.out.println();
  System.out.println(u.toString());

  if (u.estaSancionado()){

    System.out.println("Estado: Sancionado");

  }else {

    System.out.println("Estado: Activo");

  }

}else{

  System.out.println();
  System.out.println("El usuario "+numeroSocio+" no se ha encontrado");

}

  }



  private static void mostrarPrestamosActivos() {

    Prestamo[] prestamosActivos = gestor.getPrestamos();

    boolean hayPrestamos = false;

    for (int i = 0; i < gestor.getNumeroPrestamos(); i++) {

      if (prestamosActivos[i].getFechaDevolucionReal() == null){

        System.out.println();
        System.out.println(prestamosActivos[i].toString());
        hayPrestamos = true;

      }

    }

    if (!hayPrestamos){

      System.out.println();
      System.out.println("No hay préstamos activos");

    }

  }



  private static void mostrarUsuariosSancionados() {

    Usuario[] usuariosActivos = gestor.getUsuarios();

    boolean hayUsuarios = false;

    for (int i = 0; i < gestor.getNumeroPrestamos(); i++) {

      if (usuariosActivos[i].estaSancionado()){

        System.out.println(usuariosActivos[i].toString());

        hayUsuarios = true;

      }

    }

    if (!hayUsuarios){

      System.out.println();
      System.out.println("No hay usuarios registrados");

    }

  }



  private static void actualizarSanciones() {

    int contador = 0;
    LocalDate hoy = LocalDate.now();

    Usuario[] lista = gestor.getUsuarios();

    for (int i = 0; i < gestor.getNumeroUsuarios(); i++) {

      if (lista[i].estaSancionado() && lista[i].getFechaFinSancion() != null) {

        if (lista[i].getFechaFinSancion().isBefore(hoy)) {

          lista[i].levantarSancion();
          contador++;

        }

      }

    }

    System.out.println();
    System.out.println("Sanciones actualizadas. Se han levantado " + contador + " sanciones.");

  }



  }




