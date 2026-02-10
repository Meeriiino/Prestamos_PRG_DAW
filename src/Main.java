import java.time.LocalDate;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner entrada = new Scanner(System.in);
    int cont = 0;
    do {
      System.out.println("=== SISTEMA GESTIÓN BIBLIOTECA ===");
      System.out.println("1. Registrar nuevo usuario ");
      System.out.println("2. Realizar préstamo de libro");
      System.out.println("3. Devolver libro");
      System.out.println("4. Consultar estado de usuario");
      System.out.println("5. Mostrar préstamos activos");
      System.out.println("6. Mostrar usuarios sancionados");
      System.out.println("7. Actualizar sanciones");
      System.out.println("8. Salir");
      System.out.print("Escribe tu opción: ");
      cont = Integer.parseInt(entrada.nextLine());

      switch(cont){
        case 1:

          break;
        case 2:

          break;
        case 3:

          break;
        case 4:

          break;
        case 5:

          break;
        case 6:

          break;
        case 7:

          break;
        case 8:
          System.out.println("Saliendo del programa...");
          break;
        default:
          System.out.println("Opción no válida. Intente de nuevo.");
          break;
      }
      System.out.println("\nPulse intro para continuar");;
      entrada.nextLine();
    }while(cont!=8);
    entrada.close();

    //Usuario u1 = new Usuario("Sergio","sergio332@gmail.com","SOC12345", LocalDate.of(2026,02,8));

  }



  }

