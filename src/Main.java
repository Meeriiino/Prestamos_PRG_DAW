import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {


  public static void main(String[] args) throws UsuarioInvalidoException, PrestamoInvalidoException {

    Usuario u1 = new Usuario("ss","s@.","SOC12345", LocalDate.of(2026,2,2));

    System.out.println(u1.toString());


    Prestamo p1 = new Prestamo("LIB0001","D",u1, LocalDate.of(2026,2,12));

    System.out.println(p1.toString());

  }
}

