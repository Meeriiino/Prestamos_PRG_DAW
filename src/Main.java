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
          case 8 -> System.out.println("Saliendo del sistema...");
          default -> System.out.println("Opción no válida.");
        }

      } catch (NumberFormatException e) {

        System.out.println("ERROR: Introduce un número para la opción.");
      }

    } while (opcion != 8);

    }

  private static void mostrarMenu() {

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

  }


  private static void realizarPrestamo() {

    try{

      System.out.println("Introduce el código del libro (AAA0000): ");
      String codigo = entrada.nextLine();

      System.out.println("Introduce el título del libro: ");
      String titulo = entrada.nextLine();

      System.out.println("Introduce el número de socio (SOC00000): ");
      String socio = entrada.nextLine();

      Usuario u = gestor.buscarUsuario(socio);

      if (u == null) {

        System.out.println("ERROR: El usuario no existe.");
        return;

      }

      System.out.print("Fecha de préstamo (dd/mm/aaaa): ");
      LocalDate fecha = LocalDate.parse(entrada.nextLine(), formato);

      Prestamo p = gestor.realizarPrestamo(codigo, titulo, fecha, u);

      System.out.println("Préstamo realizado.");

      System.out.println("Devolución prevista: " + p.getFechaDevolucionPrevista().format(formato));

    } catch (PrestamoInvalidoException | UsuarioSancionadoException | LibroNoDisponibleException e) {

      System.out.println("ERROR: " + e.getMessage());

    } catch (DateTimeParseException e) {

      System.out.println("ERROR: Fecha no válida.");

    }

  }


  private static void devolverLibro() {

  }


  private static void consultarEstadoUsuario() {

  }


  private static void mostrarPrestamosActivos() {

  }


  private static void mostrarUsuariosSancionados() {

  }


  private static void actualizarSanciones() {

  }


}



